package com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog

import android.view.*
import android.widget.RadioButton
import android.widget.RadioGroup

import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R

class VerticalsBarsViewHolder (view: View) : RecyclerView.ViewHolder(view){

    private val radioGroup: RadioGroup = view.findViewById(R.id.vRadioGroup)

    private val radioButton: RadioButton = view.findViewById(R.id.rbColors)

    fun pintarViews(item: RadioButton){
        when(radioGroup){

        }
    }





}