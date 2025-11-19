package com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R

class VerticalsBarsAdapter (private val verticalsBarsList: List<VerticalsBars>) : RecyclerView.Adapter<VerticalsBarsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalsBarsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bar, parent, false)
        return VerticalsBarsViewHolder(view)
    }

    fun updateColors(indice: Int, newcolor: Int) {
        verticalsBarsList[indice].color = newcolor
        notifyItemChanged(indice)
    }

    override fun onBindViewHolder(holder: VerticalsBarsViewHolder, position: Int) {
        holder.pintar(verticalsBarsList[position])
    }

    override fun getItemCount(): Int = verticalsBarsList.size

}