package com.dgzc.boardgamesaspp.BoardGamesActivity

sealed class GameCategory (val isSelected: Boolean = true) {
    object Euro: GameCategory()
    object Deckbuilding: GameCategory()
    object LCG: GameCategory()
    object Legacy: GameCategory()
    object Cooperative: GameCategory()
}
