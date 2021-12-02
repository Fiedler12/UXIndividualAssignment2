package com.example.uxindividualassignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.Collections.list

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val submitBtn: Button = findViewById(R.id.submit_guess)
        val editGuess: EditText = findViewById(R.id.enter_guess)
        submitBtn.setOnClickListener {
            if (App.game.gameState == GameState.GUESS) {
                App.game.gameState = GameState.SPIN
                val guess = editGuess.text.toString()
                testGuess(guess)
                editGuess.text.clear()
            }
        }
        val spinBtn: Button = findViewById(R.id.spin)
        val testtext: TextView = findViewById(R.id.status_message)
        spinBtn.setOnClickListener {
            if (App.game.gameState == GameState.SPIN) {
                testtext.text = "You just span!"
                App.game.gameState = GameState.GUESS
            }
        }
    }

    fun testGuess(guess: String) {

    }
}