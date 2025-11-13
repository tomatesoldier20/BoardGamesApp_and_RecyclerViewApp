package com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R

class RecyclerViewActivity : AppCompatActivity() {

    private val verticalsBarsList = listOf<VerticalsBars>(
        VerticalsBars("color 1", 17894134),
        VerticalsBars("color 2", 90872543),
        VerticalsBars("color 3", 11111111),
        VerticalsBars("color 4", 98762543),
        VerticalsBars("color 5", 58342952),
        VerticalsBars("color 6", 82549022),
        VerticalsBars("color 7", 21902832),
        VerticalsBars("color 8", 67854522),
        VerticalsBars("color 9", 34567891)

    )

    private lateinit var rvVerticalBars: RecyclerView
    private lateinit var verticalsBarsAdapter: VerticalsBarsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }





    }





}