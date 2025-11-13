package com.dgzc.boardgamesaspp.BoardGamesActivity

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dgzc.boardgamesaspp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BoardgamesActivity : AppCompatActivity() {
    private val categories = listOf(
        GameCategory.LCG,
        GameCategory.Cooperative,
        GameCategory.Deckbuilding,
        GameCategory.Euro,
        GameCategory.Legacy
    )
    private val games = mutableListOf(
        Games("Frostpunk", GameCategory.Cooperative),
        Games("Hero Realm", GameCategory.Deckbuilding),
        Games("Agricola", GameCategory.Euro),
        Games("Arkham Horror", GameCategory.LCG),
        Games("Gloomhaven", GameCategory.Legacy)
    )

    private lateinit var BGAppButton: Button
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvGames: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var gamesAdapter: GamesAdapter
    private lateinit var fabAddGame: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_boardgames)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        initUI()
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        gamesAdapter = GamesAdapter(games) {position -> onGameSelected(position)}
        rvGames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGames.adapter = gamesAdapter
    }

    private fun initComponents() {
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvGames = findViewById<RecyclerView>(R.id.rvGames)
        fabAddGame = findViewById(R.id.fabAddGame)

        BGAppButton = findViewById<Button>(R.id.BGApp)
    }

    private fun initListeners() {
        fabAddGame.setOnClickListener { showDialog() }
        BGAppButton.setOnClickListener { initListeners() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_game)

        var btnAddGame: Button = dialog.findViewById(R.id.btnAddGame)
        var etGame: EditText = dialog.findViewById(R.id.etGame)
        var rgCategories = dialog.findViewById<RadioGroup>(R.id.rgCategories)

        btnAddGame.setOnClickListener {
            val currentGame = etGame.text.toString()
            if(currentGame.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById<RadioButton>(selectedId)
                val currentCategory: GameCategory = when(selectedRadioButton.text) {
                    getString(R.string.dialog_cooperative_category) -> GameCategory.Cooperative
                    getString(R.string.dialog_deckbuilding_category) -> GameCategory.Deckbuilding
                    getString(R.string.dialog_lcg_category) -> GameCategory.LCG
                    getString(R.string.dialog_legacy_category) -> GameCategory.Legacy
                    else -> GameCategory.Euro
                }
                games.add(Games(currentGame, currentCategory))
                updateGames()
                dialog.hide()
            }
        }

        dialog.show()
    }

    private fun updateGames() {
        gamesAdapter.notifyDataSetChanged()
    }
    private fun onGameSelected(position:Int){
        games[position].isSelected = !games[position].isSelected
        updateGames()
    }

}