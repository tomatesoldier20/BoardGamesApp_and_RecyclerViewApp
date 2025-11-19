package com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog // Importar AlertDialog de androidx
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R


class RecyclerViewActivity : AppCompatActivity() {

    // La lista de datos para el adapter
    private val barList = listOf(
        VerticalsBars("color 1", Color.parseColor("#000080"), "20%"),
        VerticalsBars("color 2", Color.parseColor("#FF0080"), "35%"),
        VerticalsBars("color 3", Color.parseColor("#FF0000"), "50%"),
        VerticalsBars("color 4", Color.parseColor("#FF0000"), "65%"),
        VerticalsBars("color 5", Color.parseColor("#FF0000"), "80%")
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
        rvVerticalBars.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        ) // Para scroll horizontal

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

        val rgBars = dialogView.findViewById<RadioGroup>(R.id.radioGroupBars)
        val rgColors = dialogView.findViewById<RadioGroup>(R.id.radioGroupColors)
        val btnAplicar = dialogView.findViewById<Button>(R.id.btnAddGame)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        dialog.show()

        btnAplicar.setOnClickListener {

            //Obtener barra seleccionada
            val barraSeleccionadaId = rgBars.checkedRadioButtonId
            val barraSeleccionada = dialogView.findViewById<RadioButton>(barraSeleccionadaId).text.toString()
            //Obtener color seleccionado
            val selectedColorId = rgColors.checkedRadioButtonId
            val selectedColorName =
                dialogView.findViewById<RadioButton>(selectedColorId).text.toString()
            //Convertir nombre de color a int
            val newColor = getColorFromName(selectedColorName)
            //Aplicar el cambio corresponding
            applyColorChange(barraSeleccionada, newColor)

            dialog.dismiss()
        }
    }

    private fun getColorFromName(color: String):Int {
        return when (color) {
            "color 1" -> Color.parseColor("#000080")
            "color 2" -> Color.parseColor("#FF0080")
            "color 3" -> Color.parseColor("#FF0000")
            "color 4" -> Color.parseColor("#FF0000")
            "color 5" -> Color.parseColor("#FF0000")
            else -> Color.parseColor("#000000")

        }
    }

    private fun applyColorChange(selectedBar: String, newColor: Int) {

        when (selectedBar) {

            "H1 (20%)" -> findViewById<CardView>(R.id.tvH1).setCardBackgroundColor(newColor)
            "H2 (50%)" -> findViewById<CardView>(R.id.tvH2).setCardBackgroundColor(newColor)
            "H3 (80%)" -> findViewById<CardView>(R.id.tvH3).setCardBackgroundColor(newColor)

            "V1 (20%)" -> vbAdapter.updateColors(0, newColor)   // index según orden
            "V2 (50%)" -> vbAdapter.updateColors(1, newColor)
            "V3 (80%)" -> vbAdapter.updateColors(2, newColor)
        }
    }
}
