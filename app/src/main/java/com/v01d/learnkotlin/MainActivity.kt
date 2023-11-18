package com.v01d.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //    Defining the variables
    lateinit var stopwatch: Chronometer // lateinit gets to initialize a not null property outside of the constructor
    var running = false
    var offset: Long = 0
    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stopwatch = findViewById<Chronometer>(R.id.chronometer)

        if(savedInstanceState != null){
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if(running){
                stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                stopwatch.start()
            } else setBaseTime()
        }

//        Referencing the star button
        val startButton = findViewById<Button>(R.id.start)


        startButton.setOnClickListener {
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }
        val pauseButton = findViewById<Button>(R.id.pause)
        pauseButton.setOnClickListener {
            if (running) {
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }

        val resetButton = findViewById<Button>(R.id.reset)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
            stopwatch.stop()
            running=false
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(OFFSET_KEY,offset)
        outState.putBoolean(RUNNING_KEY,running)
        outState.putLong(BASE_KEY,stopwatch.base)
        super.onSaveInstanceState(outState)
    }


    override fun onStop() {
        super.onStop()
        if(running){
            saveOffset()
            stopwatch.stop()
        }
    }

    override fun onRestart() {
        super.onRestart()
        if(running){
            setBaseTime()
            stopwatch.start()
            offset=0
        }
    }

    override fun onPause() {
        super.onPause()
        if(running){
            saveOffset()
            stopwatch.stop()
        }
    }

    override fun onResume() {
        super.onResume()
        if(running){
            setBaseTime()
            stopwatch.start()
            offset=0
        }
    }


    //        Updating stopwatch base time
    fun setBaseTime() {
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }
}




