package com.example.uxindividualassignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.Collections.list

class MainActivity : AppCompatActivity() {
    val app = App()
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
        val testtext: TextView = findViewById(R.id.status_message)
        spinBtn.setOnClickListener {
            if (app.game.gameState == GameState.SPIN) {
                testtext.text = "current state: " + app.game.word.getLetter(0)
                app.game.gameState = GameState.GUESS
            }
        }
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_word)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var dataSet = app.game.word.charList
        recyclerView.adapter = DataAdapter(dataSet)
    }

    fun testGuess(guess: String) {
        val guessChar = guess[0].toChar()
        val gameWord = app.game.word.charList
        for(i in 0..gameWord.size-1) {
            if (guessChar.equals(gameWord[i].letter)) {
                gameWord[i].isShown = true
            }
        }
        update()
    }

    fun update() {
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_word)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var dataSet = app.game.word.charList
        recyclerView.adapter = DataAdapter(dataSet)
    }
}