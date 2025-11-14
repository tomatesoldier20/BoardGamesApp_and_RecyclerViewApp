package com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R
import kotlin.collections.get

class VerticalsBarsAdapter (private val verticalsBarsList: List<VerticalsBars>, private val onItemClick: (Int) -> Unit) : RecyclerView.Adapter<VerticalsBarsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalsBarsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_recycler_view, parent, false)
        return VerticalsBarsViewHolder(view)
    }


    override fun onBindViewHolder(holder: VerticalsBarsViewHolder, position: Int) {
        holder.render(verticalsBarsList[position])
    }

    override fun getItemCount(): Int {
        return verticalsBarsList.size
    }


}