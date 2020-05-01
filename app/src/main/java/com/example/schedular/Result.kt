package com.example.schedular


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_result.view.*

/**
 * A simple [Fragment] subclass.
 */
class Result : Fragment() {

    fun comparedate(x:Int, date1: String, date2: String){
        if(date1 == date2)
            ClassData.dayresource.add(x)
    }

    fun datecheck(select: Int, optionlist: MutableList<InputData>, finalList: MutableList<InputData>){
        ClassData.dayresource.clear()
        for(x in 0 until finalList.size) {
            if(optionlist[select].date2 == "none" && finalList[x].date2 == "none")
                comparedate(x,optionlist[select].date1, finalList[x].date1)

            else if(optionlist[select].date2 == "none"){
                comparedate(x, optionlist[select].date1, finalList[x].date1)
                comparedate(x, optionlist[select].date1, finalList[x].date2)
            }
            else if(finalList[x].date2 == "none"){
                comparedate(x, optionlist[select].date1, finalList[x].date1)
                comparedate(x, optionlist[select].date2, finalList[x].date1)
            }
            else{
                comparedate(x, optionlist[select].date1, finalList[x].date1)
                comparedate(x, optionlist[select].date1, finalList[x].date2)
                comparedate(x, optionlist[select].date1, finalList[x].date1)
                comparedate(x, optionlist[select].date2, finalList[x].date1)
            }
        }
    }

    fun timecheck(select: Int, optionlist: MutableList<InputData>,finalList: MutableList<InputData>): Boolean{
        if(ClassData.dayresource.size != 0){
            for(k in 0 until ClassData.dayresource.size){
                if(optionlist[select].hour1 > finalList[ClassData.dayresource[k]].hour2 ||
                    (optionlist[select].hour1 == finalList[ClassData.dayresource[k]].hour2) && (optionlist[select].minute1 > finalList[ClassData.dayresource[k]].minute2) ||
                    optionlist[select].hour2 < finalList[ClassData.dayresource[k]].hour1 ||
                    (optionlist[select].hour2 == finalList[ClassData.dayresource[k]].hour1) && (optionlist[select].minute1 > finalList[ClassData.dayresource[k]].minute2)){
                }
                else return false
            }
        }
        return true
    }


    //Add cases and key.
    fun addtoResult(finalList: MutableList<InputData>, k:Int){
        for(n in 0 until finalList.size){
            val ml1 = arraycopy(finalList[n].Class, finalList[n].date1, finalList[n].date2, finalList[n].hour1, finalList[n].minute1, finalList[n].hour2, finalList[n].minute2, finalList[n].place, k)
            ClassData.resultclass.add(ml1)
        }
    }


    fun calculate(size: Int, require: Int, credit1: Int, credit2: Int, select: Int, count: Int, optionlist: MutableList<InputData>, resetList: MutableList<InputData>){
        var t = ClassData.resultclass.size
        if(t == 0)  t = 0
        else t = ClassData.resultclass[t-1].k + 1

        val reset = mutableListOf<InputData>()
        for(i in 0 until resetList.size){
            reset.add(resetList[i])
        }

        if(select < size) {
            datecheck(select,optionlist,reset)  //checking date - Place overlapping dates in an array.
            if(timecheck(select,optionlist,reset)){ //checking time - If time overlaps, return false. If not, return true.
                if((credit1 <= 3*(require + count + 1)) && (3*(require + count + 1) <= credit2)){   //checking credit.
                    //first case - If credit is correct, add in the number of cases and iterate.
                    reset.add(optionlist[select])
                    addtoResult(reset, t)   //add cases and key.
                    calculate(size, require, credit1, credit2, select + 1, count + 1, optionlist, reset)
                } else{
                    //If credit is wrong, just iterate.
                    reset.add(optionlist[select])
                    calculate(size, require, credit1, credit2, select + 1, count + 1, optionlist, reset)
                }
                //Even if your grades are correct, not add and iterate.
                reset.removeAt(reset.size -1)
                calculate(size, require, credit1, credit2, select + 1, count, optionlist, reset)
            }
        }
    }

    // making button
    fun AddButton(k: Int): Button{
        val x  = Button(activity!!.applicationContext)
        val case = k + 1
        x.text = "Case $case"
        x.setOnClickListener {
            val action = ResultDirections.actionResultToSchedule(k)
            it.findNavController().navigate(action)
        }
        return x
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        val u = layoutInflater.inflate(R.layout.fragment_result,container,false)
        val optionlist = mutableListOf<InputData>()
        val reset1 = mutableListOf<InputData>()
        for(n in 0 until ClassData.classes.size) {
            optionlist.add(ClassData.classes[n])
        }
        val finalList = mutableListOf<InputData>()

        var x = 0
        while(x < optionlist.size){
            if(optionlist[x].Class == ClassData.require[0] || optionlist[x].Class == ClassData.require[1] || optionlist[x].Class == ClassData.require[2]){
                finalList.add(optionlist[x])
                optionlist.removeAt(x)
            }
            else
                x++
        }

        for (i in 0 until finalList.size){
            reset1.add(finalList[i])
        }

        if((ClassData.credit[0] <= 3*(ClassData.classes.size - optionlist.size) && (3 * ( ClassData.classes.size - optionlist.size) <= ClassData.credit[1]))){
            addtoResult(reset1,0)
        }

        //recursive function
        calculate(optionlist.size, ClassData.classes.size - optionlist.size, ClassData.credit[0], ClassData.credit[1], 0,0, optionlist, finalList)

        var numofcase = 0

        //Calculate the number of cases by using key.
        if(ClassData.resultclass.size != 0) {
            for (l in 0 until ClassData.resultclass.size){
                if(numofcase == ClassData.resultclass[l].k){
                    u.MyScrollView.MyLinearLayout.addView(AddButton(numofcase))
                    numofcase++
                }
            }
        }

        // Inflate the layout for this fragment
        return u
    }




}
