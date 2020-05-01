package com.example.schedular


import android.app.ActionBar
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_dialog.*
import kotlinx.android.synthetic.main.fragment_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var appAdapter: AppAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home,container,false)
        setHasOptionsMenu(true)
        v.class_list.setHasFixedSize(true)

        appAdapter = AppAdapter(ClassData.classes)
        v.class_list.adapter = AppAdapter(ClassData.classes)
        appAdapter.notifyItemInserted(ClassData.classes.size-1)
        v.class_list.addItemDecoration(itemdecoration(15))

        // Inflate the layout for this fragment
        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        Add_Class_Button.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_dialog)
        }

        Next_to_Condition.setOnClickListener{
            navController.navigate(R.id.action_homeFragment_to_condition)
        }

    }

}
