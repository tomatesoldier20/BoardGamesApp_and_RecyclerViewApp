package com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog

import android.graphics.Paint
import android.view.*
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R

class VerticalsBarsViewHolder (view: View) : RecyclerView.ViewHolder(view){

    private val background = view.findViewById<CardView>(R.id.cvVerticalBarBackground)//valor que se va a cambiar por parte del TextView correspondiente

    private val texto = view.findViewById<TextView>(R.id.tvVerticalBarTextName)// va a indicar donde se va a cambiar el color del CardView


    fun pintar(item: VerticalsBars){
    // Tachar si está seleccionado
    if (item.isSelected) {
        texto.paintFlags = texto.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        texto.paintFlags = texto.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
    // Texto
        texto.text = "${item.name} (${item.porcentaje})"
    // Color del fondo
        background.setCardBackgroundColor(item.color)
    }








}