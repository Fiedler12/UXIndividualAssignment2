package com.example.uxindividualassignment2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val app = App()
    var pointsForGuess: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val submitBtn: Button = findViewById(R.id.submit_guess)
        val editGuess: EditText = findViewById(R.id.enter_guess)
        submitBtn.setOnClickListener {
            if (app.game.gameState == GameState.GUESS) {
                app.game.gameState = GameState.SPIN
                val guess = editGuess.text.toString()
                testGuess(guess)
                editGuess.text.clear()
            }
        }
        val spinBtn: Button = findViewById(R.id.spin)
        spinBtn.setOnClickListener {
            if (app.game.gameState == GameState.SPIN) {
                spin()
            }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_word)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dataSet = app.game.word.charList
        recyclerView.adapter = DataAdapter(dataSet)
    }

    fun testGuess(guess: String) {
        val health: TextView = findViewById(R.id.health)
        val points: TextView = findViewById(R.id.points)
        val guessChar = guess[0].uppercaseChar()
        val gameWord = app.game.word.charList
        var correctCounter = 0
        for(i in 0..gameWord.size-1) {
            if (guessChar.equals(gameWord[i].letter)) {
                gameWord[i].isShown = true
                correctCounter++
            }
        }
        if (correctCounter > 0) {
            app.game.points = app.game.points + pointsForGuess * correctCounter
            points.text = app.game.points.toString()
            giveMessage(guessChar.toString() + " is in the word.")
        } else {
            app.game.health--
            health.text = app.game.health.toString()
            giveMessage(guessChar.toString() + " is not in this word. You lose one HP.")
        }
        update()
    }

    fun spin() {
        val random = (1..10).random()
        val statusMessage: TextView = findViewById(R.id.status_message)
        val health: TextView = findViewById(R.id.health)
        when(random){
            1 -> {
                pointsForGuess = 100
                app.game.gameState = GameState.GUESS
                statusMessage.text = "You will get 100 points for a correct consonant! Please guess."
            }
            2 -> {
                pointsForGuess = 200
                app.game.gameState = GameState.GUESS
                statusMessage.text = "You will get 200 points for a correct consonant! Please guess."            }
            3 -> {
                pointsForGuess = 400
                app.game.gameState = GameState.GUESS
                statusMessage.text = "You will get 400 points for a correct consonant! Please guess."
            }
            4 -> {
                pointsForGuess = 800
                app.game.gameState = GameState.GUESS
                statusMessage.text = "You will get 800 points for a correct consonant! Please guess."
            }
            5 -> {
                pointsForGuess = 1600
                app.game.gameState = GameState.GUESS
                statusMessage.text = "You will get 1600 points for a correct consonant! Please guess."
            }
            6 -> {
                pointsForGuess = 2000
                app.game.gameState = GameState.GUESS
                statusMessage.text = "You will get 2000 points for a correct consonant! Please guess."
            }
            7 -> {
                pointsForGuess = 2500
                app.game.gameState = GameState.GUESS
                statusMessage.text = "You will get 2500 points for a correct consonant! Please guess."
            }
            8 -> {
                pointsForGuess = 3000
                app.game.gameState = GameState.GUESS
                statusMessage.text = "You will get 3000 points for a correct consonant! Please guess."
            }
            9 -> {
                statusMessage.text = "You get an ekstra life!"
                app.game.health++
                health.text = app.game.health.toString()
            }
            10 -> {
                statusMessage.text = "You lose a life.."
                app.game.health--
                health.text = app.game.health.toString()
            }
        }
    }

    fun update() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_word)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val dataSet = app.game.word.charList
        recyclerView.adapter = DataAdapter(dataSet)
    }

    fun checkWin() {
        var allGuessed: Boolean = true
        for(i in 0..app.game.word.charList.size-1) {
            if (app.game.word.charList[i].isShown == false) {
                allGuessed = false
            }
            if(allGuessed) {
                TODO("Win fragment")
            }
        }
    }

    fun checkLose() {
        if(app.game.health <= 0) {
            TODO("Lose fragment")
        }
    }

    fun giveMessage(message: String) {
        val statusMessage: TextView = findViewById(R.id.status_message)
        statusMessage.text = message
    }
}