package com.example.schedular


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_condition.*
import kotlinx.android.synthetic.main.fragment_condition.view.*

/**
 * A simple [Fragment] subclass.
 */
class Condition : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = layoutInflater.inflate(R.layout.fragment_condition, container,false)
        val selectclass = mutableListOf<String>()   //Class which input. It is used by putting data to spinner.
        val size = ClassData.classes.size
        for(x in 0 until size){
            selectclass.add(ClassData.classes[x].Class)
        }
        selectclass.add("none")
        val arrayAdapter = ArrayAdapter(activity!!.applicationContext, android.R.layout.simple_spinner_item,selectclass)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.required_spinner1.adapter = arrayAdapter
        v.required_spinner2.adapter = arrayAdapter
        v.required_spinner3.adapter = arrayAdapter

        ClassData.resultclass.clear()
        // Inflate the layout for this fragment
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Next_to_Result.setOnClickListener {

            ClassData.require.add(required_spinner1.selectedItem.toString())
            ClassData.require.add(required_spinner2.selectedItem.toString())
            ClassData.require.add(required_spinner3.selectedItem.toString())
            ClassData.credit.add(Integer.parseInt(credit1.text.toString()))
            ClassData.credit.add(Integer.parseInt(credit2.text.toString()))

            it.findNavController().navigate(R.id.action_condition_to_result)
        }
    }

}
