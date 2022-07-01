package com.example.kennygame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Level : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        val easyActivity = findViewById<Button>(R.id.easy)
        easyActivity.setOnClickListener {
            val intent = Intent(this, Game::class.java).apply {
                putExtra("Level","500")
            }
            startActivity(intent)
        }

        val mediumActivity = findViewById<Button>(R.id.medium)
        mediumActivity.setOnClickListener {
            val intent = Intent(this, Game::class.java).apply {
                putExtra("Level","400")
            }
            startActivity(intent)
        }

        val hardActivity = findViewById<Button>(R.id.hard)
        hardActivity.setOnClickListener {
            val intent = Intent(this, Game::class.java).apply {
                putExtra("Level","300")
            }
            startActivity(intent)
        }
    }
}