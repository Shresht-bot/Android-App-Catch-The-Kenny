package com.example.kennygame

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var highscore: Int = 0
    var lastscore: Int = 0
    lateinit var head: TextView
    lateinit var image: ImageView
    lateinit var start: Button
    lateinit var rule_button: Button
    lateinit var rules: TextView
    lateinit var back: Button
    lateinit var high_Score : TextView
    lateinit var last_Score : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        head = findViewById<TextView>(R.id.title)
        image = findViewById<ImageView>(R.id.image)
        start = findViewById<Button>(R.id.start)
        rule_button = findViewById<Button>(R.id.rule_button)
        rules = findViewById<TextView>(R.id.rules)
        back = findViewById<Button>(R.id.back)
        high_Score = findViewById<TextView>(R.id.high_Score)
        last_Score = findViewById<TextView>(R.id.last_Score)

        val startActivity = findViewById<Button>(R.id.start)
        startActivity.setOnClickListener {
            val intent = Intent(this, Level::class.java).apply { }
            startActivity(intent)
        }

        val preferences: SharedPreferences = getSharedPreferences("PREFS", 0)
        lastscore = preferences.getInt("lastScore",0)
        highscore = preferences.getInt("highScore",0)
        if(lastscore > highscore){
            highscore = lastscore
            val editor = preferences.edit()
            editor.putInt("highScore", highscore)
            editor.apply()

        }
        last_Score.text = "Last Score : " + lastscore
        high_Score.text = "Highest Score : " + highscore

    }

    fun rule(view: View){
        head.visibility = View.INVISIBLE
        image.visibility = View.INVISIBLE
        start.visibility = View.INVISIBLE
        rule_button.visibility = View.INVISIBLE
        high_Score.visibility = View.INVISIBLE
        last_Score.visibility = View.INVISIBLE
        rules.visibility = View.VISIBLE
        back.visibility = View.VISIBLE
    }

    fun back(view: View){
        head.visibility = View.VISIBLE
        image.visibility = View.VISIBLE
        start.visibility = View.VISIBLE
        rule_button.visibility = View.VISIBLE
        high_Score.visibility = View.VISIBLE
        last_Score.visibility = View.VISIBLE
        rules.visibility = View.INVISIBLE
        back.visibility = View.INVISIBLE
    }
}
