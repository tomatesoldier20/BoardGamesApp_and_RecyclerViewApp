package com.dgzc.boardgamesaspp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog.RecyclerViewActivity
import com.dgzc.boardgamesaspp.BoardGamesActivity.BoardgamesActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var BGApps = findViewById<Button>(R.id.BGApp)
        BGApps.setOnClickListener { navigateToBoardGamesApp() }

        var AppRV = findViewById<Button>(R.id.AppRV)
        AppRV.setOnClickListener { navigateToAppRV() }

    }

    private fun navigateToBoardGamesApp(){
        var intent = Intent(this, BoardgamesActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAppRV(){
        var intent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(intent)
    }


}