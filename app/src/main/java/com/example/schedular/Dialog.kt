package com.example.schedular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_dialog.*


/**
 * A simple [Fragment] subclass.
 */
class Dialog : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Next_to_Home.setOnClickListener {
            //Delivering class information which is input.
            val cl = InputData(Class_name.text.toString(), First_date.selectedItem.toString(), Second_date.selectedItem.toString(), Integer.parseInt(hour1.selectedItem.toString()), Integer.parseInt(minute1.selectedItem.toString()), Integer.parseInt(hour2.selectedItem.toString()), Integer.parseInt(minute2.selectedItem.toString()), Place_name.text.toString())
            ClassData.classes.add(cl)

            it.findNavController().navigate(R.id.action_dialog_to_homeFragment)
        }
    }

}
