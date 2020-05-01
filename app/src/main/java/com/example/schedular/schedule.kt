package com.example.schedular


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.convertTo
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*

/**
 * A simple [Fragment] subclass.
 */
class schedule : Fragment() {

    fun timewhen1(hour:Int): Int{
        val x = when(hour){
            9 -> 0
            10 -> 1
            11 -> 2
            12 -> 3
            13 -> 4
            14 -> 5
            15 -> 6
            16 -> 7
            17 -> 8
            18 -> 9
            19 -> 10
            20 -> 11
            21 -> 12
            22 -> 13
            else -> 99
        }
        return x
    }

    fun timewhen2(hour:Int): Int{
        val x = when(hour){
            10 -> 0
            11 -> 1
            12 -> 2
            13 -> 3
            14 -> 4
            15 -> 5
            16 -> 6
            17 -> 7
            18 -> 8
            19 -> 9
            20 -> 10
            21 -> 11
            22 -> 12
            else -> 99
        }
        return x
    }


    fun shellselect(date:String, hour:Int, monday: Array<TextView>, tuesday: Array<TextView>, wednesday: Array<TextView>, thursday: Array<TextView>, friday: Array<TextView>, none1: Array<TextView>): TextView{
        val t = when(date){
            "Monday" -> monday[hour]
            "Tuesday" -> tuesday[hour]
            "Wednesday" -> wednesday[hour]
            "Thursday" -> thursday[hour]
            "Friday" -> friday[hour]
            else -> none1[0]
        }
        return t
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = layoutInflater.inflate(R.layout.fragment_schedule, container,false)
        val monday = arrayOf<TextView>(
            v.monday0,
            v.monday1,
            v.monday2,
            v.monday3,
            v.monday4,
            v.monday5,
            v.monday6,
            v.monday7,
            v.monday8,
            v.monday9,
            v.monday10,
            v.monday11,
            v.monday12,
            v.monday13)

        val tuesday = arrayOf<TextView>(
            v.tuesday0,
            v.tuesday1,
            v.tuesday2,
            v.tuesday3,
            v.tuesday4,
            v.tuesday5,
            v.tuesday6,
            v.tuesday7,
            v.tuesday8,
            v.tuesday9,
            v.tuesday10,
            v.tuesday11,
            v.tuesday12,
            v.tuesday13)

        val wednesday = arrayOf<TextView>(
            v.wednesday0,
            v.wednesday1,
            v.wednesday2,
            v.wednesday3,
            v.wednesday4,
            v.wednesday5,
            v.wednesday6,
            v.wednesday7,
            v.wednesday8,
            v.wednesday9,
            v.wednesday10,
            v.wednesday11,
            v.wednesday12,
            v.wednesday13)

        val thursday = arrayOf<TextView>(
            v.thursday0,
            v.thursday1,
            v.thursday2,
            v.thursday3,
            v.thursday4,
            v.thursday5,
            v.thursday6,
            v.thursday7,
            v.thursday8,
            v.thursday9,
            v.thursday10,
            v.thursday11,
            v.thursday12,
            v.thursday13)

        val friday = arrayOf<TextView>(
            v.friday0,
            v.friday1,
            v.friday2,
            v.friday3,
            v.friday4,
            v.friday5,
            v.friday6,
            v.friday7,
            v.friday8,
            v.friday9,
            v.friday10,
            v.friday11,
            v.friday12,
            v.friday13)

        val none1 = arrayOf<TextView>(v.none)


        val list = mutableListOf<arraycopy>()
        val temp = arguments
        if(temp != null) {
            val casenum = scheduleArgs.fromBundle(temp).casenumber
            for (i in 0 until ClassData.resultclass.size) {
                if (ClassData.resultclass[i].k == casenum)
                    list.add(ClassData.resultclass[i])
            }
        }

        for(j in 0 until list.size){
            val xdate= list[j].date1
            val ydate= list[j].date2
            val xhour = timewhen1(list[j].hour1)
            val yhour = timewhen2(list[j].hour2)

            for(k in xhour..yhour){
                val q = shellselect(xdate, k, monday, tuesday, wednesday, thursday, friday, none1)
                if(q != none1[0]){
                    q.setBackgroundResource(R.color.colorshell)
                    q.text = list[j].Class
                }
                val p = shellselect(ydate, k, monday, tuesday, wednesday, thursday, friday, none1)
                if(p != none1[0]){
                    p.setBackgroundResource(R.color.colorshell)
                   p.text = list[j].Class
                }
            }
        }
        ClassData.resultclass.clear()

        // Inflate the layout for this fragment
        return v
    }


}
