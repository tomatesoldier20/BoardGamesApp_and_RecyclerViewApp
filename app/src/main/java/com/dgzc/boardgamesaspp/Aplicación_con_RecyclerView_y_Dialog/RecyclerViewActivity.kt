package com.dgzc.boardgamesaspp.Aplicación_con_RecyclerView_y_Dialog

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog // Importar AlertDialog de androidx
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R


class RecyclerViewActivity : AppCompatActivity() {

    // La lista de datos para el adapter
    private val barList = listOf(
        VerticalsBars("V1 (20%)", Color.parseColor("#330156FF"), "20%"),
        VerticalsBars("V2 (35%)", Color.parseColor("#5901FF01"), "35%"),
        VerticalsBars("V3 (50%)", Color.parseColor("#80FF0101"), "50%"),
        VerticalsBars("V4 (65%)", Color.parseColor("#A6FF01FF"), "65%"),
        VerticalsBars("v5 (80%)", Color.parseColor("#CCFFFF01"), "80%")
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
        rvVerticalBars.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        vbAdapter = VerticalsBarsAdapter(barList)
        rvVerticalBars.adapter = vbAdapter

        btnChangeColor.setOnClickListener {
            showColorChangeDialog()
        }
    }

    /**
     * @author Gabriel Orlando Cruz Parraga
     *
     * @name showColorChangeDialog
     * Muestra el diálogo para cambiar el color de fondo de la barra vertical, y una vez finalizado la logica del método
     * .getColorFromName() cerrará el AlertDialog.
     *
     * @see AlertDialog // Es una clase Heredada de Dialog que crea ventanas emergentes, la diferencia principal entre su padre (Dialog) y AlertDialog
     * que AlertDialog genera elementos especiales como un Titulo, botones, un mensaje, y otros elementos que la Clase Padre (Dialog) no lo hace.
     * @see Builder // Es una clase auxiliar que sirve para crear construcctores de diferentes Clases, en este caso sirve para definir el constructor
     * de la Clase AlertDialog.
     *
     * @property layoutInflater // Propiedad heredad de la Clase ContextThemeWrapper que devuelve una instancia.
     *
     * @see layoutInflater // Herramienta de Android que se encarga de transformar archivos XML en vistas aplicables al código.
     * @see layoutInflater.inflate // Metodo encargado de leer un archivo XML y transformar los elementos que constan dentro
     * del archivo para hacerlos elementos interactuables dentro del código.
     * @see AlertDialog.Builder.setView // Este método, implementado dentro de AlertDialog y en Builder, permite insertar una
     * vista personalizada (un archivo XML). Pide como parametro la vista a insertar
     */
    private fun showColorChangeDialog() {
        val dialogView = layoutInflater.inflate(R.layout.activity_emergentview, null)

        val rgBars = dialogView.findViewById<RadioGroup>(R.id.radioGroupBars)
        val rgColors = dialogView.findViewById<RadioGroup>(R.id.radioGroupColors)
        val btnAplicar = dialogView.findViewById<Button>(R.id.btnChangeColor)

        val dialog = AlertDialog.Builder(this).setView(dialogView).create()

        dialog.show() // Muestra la ventana Emergente

        btnAplicar.setOnClickListener {
            //Obtener barra seleccionada
            val barraSeleccionadaId = rgBars.checkedRadioButtonId
            val barraSeleccionada = dialogView.findViewById<RadioButton>(barraSeleccionadaId).text.toString()
            //Obtener color seleccionado
            val idColorSeleccionado = rgColors.checkedRadioButtonId
            val nombreColorSeleccionado = dialogView.findViewById<RadioButton>(idColorSeleccionado).text.toString()
            //Convertir nombre de color a int
            val colorNuevo = getColorFromName(nombreColorSeleccionado, barraSeleccionada)
            //Aplicar el cambio corresponding
            aplicarColor(barraSeleccionada, colorNuevo)

            dialog.dismiss() // Oculta la ventana Emergente
        }
    }

    /**
     * @author Gabriel Orlando Cruz Parraga
     *
     * @name getColorFromName
     * Convierte el nombre de color en un valor hexadecimal correspondiente.
     *
     * Esta función pide como parametros $color y $barraSeleccionada para identificar el color al cual se quiere cambiar
     * y la barra a la que hay que modificar teniendo de referencia el la variable $barraSeleccionada. Dependiendo
     * del contenido de variable $barraSeleccionada, aplicará un color con una opacidad diferentes. Y en el caso del color,
     * este cambiará en base al contenido de la variable $color.
     *
     * @see ContextCompat Es una clase estátcia que permite obtener, de forma segura, recursos, comprobar permisos de ejecución
     * y manejar la compatibilidad de archivos de diferentes versiones de Android.
     * @see ContextCompat.getColor Esta función estática dentro de la Clase ContextCompat permite conseguir de forma segura el color del archivo XML color.
     * Pide como parametro del recurso al que se va a acceder y el nombre del color a convertir.
     *
     * @param color El nombre del color a convertir.
     * @param barraSeleccionada La barra seleccionada.
     *
     * @return El valor hexadecimal correspondiente al color.
     */
    private fun getColorFromName(color: String, barraSeleccionada: String):Int {
        return when (color) {
            "Azul" -> {
                when(barraSeleccionada){
                    "H1 (20%)", "V1 (20%)" -> ContextCompat.getColor(this, R.color.Blue_20)
                    "V2 (35%)" -> ContextCompat.getColor(this, R.color.Blue_35)
                    "H2 (50%)", "V3 (50%)" -> ContextCompat.getColor(this, R.color.Blue_50)
                    "V4 (65%)" -> ContextCompat.getColor(this, R.color.Blue_65)
                    else -> ContextCompat.getColor(this, R.color.Blue_80) // H3 (80%) y V5 (80%)
                }
            }
            "Rojo" -> {
                when(barraSeleccionada){
                    "H1 (20%)", "V1 (20%)" -> ContextCompat.getColor(this, R.color.Red_20)
                    "V2 (35%)" -> ContextCompat.getColor(this, R.color.Red_35)
                    "H2 (50%)", "V3 (50%)" -> ContextCompat.getColor(this, R.color.Red_50)
                    "V4 (65%)" -> ContextCompat.getColor(this, R.color.Red_65)
                    else -> ContextCompat.getColor(this, R.color.Red_80)
                }
            }
            "Verde" -> {
                when(barraSeleccionada){
                    "H1 (20%)", "V1 (20%)" -> ContextCompat.getColor(this, R.color.Green_20)
                    "V2 (35%)" -> ContextCompat.getColor(this, R.color.Green_35)
                    "H2 (50%)", "V3 (50%)" -> ContextCompat.getColor(this, R.color.Green_50)
                    "V4 (65%)" -> ContextCompat.getColor(this, R.color.Green_65)
                    else -> ContextCompat.getColor(this, R.color.Green_80)
                }
            }
            "Amarillo" -> {
                when(barraSeleccionada){
                    "H1 (20%)", "V1 (20%)" -> ContextCompat.getColor(this, R.color.Yellow_20)
                    "V2 (35%)" -> ContextCompat.getColor(this, R.color.Yellow_35)
                    "H2 (50%)", "V3 (50%)" -> ContextCompat.getColor(this, R.color.Yellow_50)
                    "V4 (65%)" -> ContextCompat.getColor(this, R.color.Yellow_65)
                    else -> ContextCompat.getColor(this, R.color.Yellow_80)
                }
            }
            "Morado" -> {
                when(barraSeleccionada){
                    "H1 (20%)", "V1 (20%)" -> ContextCompat.getColor(this, R.color.Purple_20)
                    "V2 (35%)" -> ContextCompat.getColor(this, R.color.Purple_35)
                    "H2 (50%)", "V3 (50%)" -> ContextCompat.getColor(this, R.color.Purple_50)
                    "V4 (65%)" -> ContextCompat.getColor(this, R.color.Purple_65)
                    else -> ContextCompat.getColor(this, R.color.Purple_80)
                }
            }
            "Naranja" -> {
                when(barraSeleccionada){
                    "H1 (20%)", "V1 (20%)" -> ContextCompat.getColor(this, R.color.Orange_20)
                    "V2 (35%)" -> ContextCompat.getColor(this, R.color.Orange_35)
                    "H2 (50%)", "V3 (50%)" -> ContextCompat.getColor(this, R.color.Orange_50)
                    "V4 (65%)" -> ContextCompat.getColor(this, R.color.Orange_65)
                    else -> ContextCompat.getColor(this, R.color.Orange_80)
                }
            }
            "Granate" -> {
                when(barraSeleccionada){
                    "H1 (20%)", "V1 (20%)" -> ContextCompat.getColor(this, R.color.Garnet_20)
                    "V2 (35%)" -> ContextCompat.getColor(this, R.color.Garnet_35)
                    "H2 (50%)", "V3 (50%)" -> ContextCompat.getColor(this, R.color.Garnet_50)
                    "V4 (65%)" -> ContextCompat.getColor(this, R.color.Garnet_65)
                    else -> ContextCompat.getColor(this, R.color.Garnet_80)
                }
            }
            "Gris" -> {
                when(barraSeleccionada){
                    "H1 (20%)", "V1 (20%)" -> ContextCompat.getColor(this, R.color.Gray_20)
                    "V2 (35%)" -> ContextCompat.getColor(this, R.color.Gray_35)
                    "H2 (50%)", "V3 (50%)" -> ContextCompat.getColor(this, R.color.Gray_50)
                    "V4 (65%)" -> ContextCompat.getColor(this, R.color.Gray_65)
                    else -> ContextCompat.getColor(this, R.color.Gray_80)
                }
            }
            else -> Color.parseColor("#000000")

        }
    }
    /**
     * @author Gabriel Orlando Cruz Parraga
     *
     * @name aplicarColor
     *
     * Este método aplica el color que selecciona el metodo $getColorFromName a la barra seleccionada en $showColorChangeDialog.
     * El cambio de color se aplica con el método de .setCardBackgroundColor() de la Clase CardView en el caso de los CardViews Horizontales,
     * en el caso del recicler view, con un método en le adapter del RecyclerView, se actualizan con el método .udpateColors().
     *
     *
     * @param barraSeleccionada // La barra seleccionada.
     * @param nuevoColor // El nuevo color a aplicar.
     */
    private fun aplicarColor(barraSeleccionada: String, nuevoColor: Int) {

        when (barraSeleccionada) {

            "H1 (20%)" -> findViewById<CardView>(R.id.tvH1).setCardBackgroundColor(nuevoColor)
            "H2 (50%)" -> findViewById<CardView>(R.id.tvH2).setCardBackgroundColor(nuevoColor)
            "H3 (80%)" -> findViewById<CardView>(R.id.tvH3).setCardBackgroundColor(nuevoColor)

            "V1 (20%)" -> vbAdapter.updateColors(0, nuevoColor)
            "V2 (35%)" -> vbAdapter.updateColors(1, nuevoColor)
            "V3 (50%)" -> vbAdapter.updateColors(2, nuevoColor)
            "V4 (65%)" -> vbAdapter.updateColors(3, nuevoColor)
            "V5 (80%)" -> vbAdapter.updateColors(4, nuevoColor)

        }
    }
}
