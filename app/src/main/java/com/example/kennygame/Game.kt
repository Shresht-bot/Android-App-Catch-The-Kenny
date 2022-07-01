package com.example.kennygame

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import kotlin.collections.ArrayList

class Game : AppCompatActivity() {

    private var score: Int = 0
    var imageArray = ArrayList<ImageView>()
    var handler: Handler = Handler()
    var runnable:Runnable = Runnable {  }
    var level: Long = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val diff: String? = intent.getStringExtra("Level")
        diff?.let {
            level = it.toLong()
            }

        val quitActivity = findViewById<Button>(R.id.quit)
        val listener = View.OnClickListener {
            val preferences: SharedPreferences = getSharedPreferences("PREFS", 0)
            val editor = preferences.edit()
            editor.putInt("lastScore", score)
            editor.apply()
            val intent = Intent(this,MainActivity::class.java).apply {  }
            startActivity(intent)
            finish()
        }
        quitActivity.setOnClickListener(listener)

        val restartActivity = findViewById<Button>(R.id.restart)
        restartActivity.setOnClickListener {
            val intent = Intent(this, Game::class.java).apply {  }
            startActivity(intent)
        }

        score = 0
        imageArray = arrayListOf(kenny00,kenny01,kenny02,kenny10,kenny11,kenny12,kenny20,kenny21,kenny22)

        hideImages()

        object: CountDownTimer(20000,1000){
            override fun onFinish() {
                timer.text = "Time's UP"
                handler.removeCallbacks(runnable)
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }
            }

            override fun onTick(p0: Long) {
                timer.text = "Time: " + p0/1000
            }
        }.start()
    }

    private fun hideImages(){
        runnable = object: Runnable{
            override fun run() {
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val index = random.nextInt(8-0)
                imageArray[index].visibility = View.VISIBLE
                handler.postDelayed(runnable, level)
            }
        }
        handler.post(runnable)
    }

    fun increaseScore(view: View){
        score ++
        scoreText.text = "Score: " + score
    }
}
