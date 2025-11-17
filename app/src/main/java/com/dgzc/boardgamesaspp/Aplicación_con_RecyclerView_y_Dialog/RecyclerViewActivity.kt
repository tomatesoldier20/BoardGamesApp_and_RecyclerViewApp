package com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog // Importar AlertDialog de androidx
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R



class RecyclerViewActivity : AppCompatActivity() {

    // La lista de datos para el adapter
    private val barList = listOf(
        VerticalsBars("color 1", 17894134, "20%"),
        VerticalsBars("color 2", 90872543, "35%"),
        VerticalsBars("color 3", 11111111, "50%"),
        VerticalsBars("color 4", 98762543, "65%"),
        VerticalsBars("color 5", 58342952, "80%")
    )

    // Declaración de las vistas
    private lateinit var rvVerticalBars: RecyclerView
    private lateinit var btnChangeColor: CardView
    private lateinit var vbAdapter: VerticalsBarsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        //Vistas layout
        rvVerticalBars = findViewById(R.id.rvVerticalsBars)
        btnChangeColor = findViewById(R.id.cvButton)
        //RecyclerView
        rvVerticalBars.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) // Para scroll horizontal

        vbAdapter = VerticalsBarsAdapter(barList)
        rvVerticalBars.adapter = vbAdapter

        btnChangeColor.setOnClickListener {
            showColorChangeDialog()
        }
    }
    /*
     *
     * Muestra el diálogo para cambiar el color de fondo de la barra vertical.
     *
     * @property dialogView
     * @property dialog
     * @property btnAplicar
     * @see layoutInflater // Herramienta de Android que se encarga de transformar archivos XML en vistas aplicables al código.
     * @see layoutInflater.inflate // Metodo encargado de leer un archivo XML y transformar los elementos que constan dentro
     * del archivo para hacerlos elementos interactuables dentro del código.
     */
    private fun showColorChangeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.activity_emergentview, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .create()

        dialog.show()
        val btnAplicar = dialogView.findViewById<Button>(R.id.btnAddGame)

        btnAplicar.setOnClickListener {
            dialog.dismiss()
        }
    }
}
