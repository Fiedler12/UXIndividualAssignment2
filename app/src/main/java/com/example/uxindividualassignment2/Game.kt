package com.example.uxindividualassignment2

class Game {
    var gameState: GameState
    var points: Int
    var gameIsActive: Boolean
    var word: Word

    init {
        gameState = GameState.SPIN
        points = 0
        gameIsActive = true
        word = Word("Further")
    }

    fun next() {
        if (gameState == GameState.SPIN) {
            gameState = GameState.GUESS
        }
        else {
            gameState = GameState.SPIN
        }
    }
}