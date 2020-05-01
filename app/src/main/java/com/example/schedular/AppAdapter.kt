package com.example.schedular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_layout.view.*

class AppViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.item_layout,parent,false)) {

    fun bind(cl: InputData){

        val a = if(cl.hour1 < 10) {"0" + cl.hour1.toString()}
        else cl.hour1
        val b = if(cl.minute1 < 10) {"0" + cl.minute1.toString()}
        else cl.minute1
        val c = if(cl.hour2 < 10) {"0" + cl.hour2.toString()}
        else cl.hour2
        val d = if(cl.minute2 < 10) {"0" + cl.minute2.toString()}
        else cl.minute2

        itemView.Classname.text = "${cl.Class}"

        if(cl.date2 == "none")
            itemView.Datename.text = "${cl.date1}"
        else
            itemView.Datename.text = "${cl.date1}, ${cl.date2}"

        itemView.Timename.text = "$a:$b ~ $c:$d "
        itemView.Placename.text = "${cl.place}"


    }
}

class AppAdapter(private var classes: MutableList<InputData>): RecyclerView.Adapter<AppViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AppViewHolder(inflater, parent)
    }

    override fun getItemCount() = classes.size

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {


        holder.bind(classes[position])
    }
}