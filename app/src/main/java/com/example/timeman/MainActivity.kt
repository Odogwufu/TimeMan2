package com.example.timeman

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.example.timeman.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//button listener
        binding.startStopButton.setOnClickListener { startStopTimer() }
        binding.resetButton.setOnClickListener { resetTimer() }

        serviceIntent = Intent(applicationContext, TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        val button =findViewById<Button>(R.id.secondActivityBtn)
        button.setOnClickListener{
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

    }

    private fun startStopTimer() {
        if (timerStarted) {
            stopTimer()
        } else {
            startTimer()
        }
    }
// Start Timer
    private fun startTimer() {
        serviceIntent.putExtra(TimerService.TIME_EXTRA, time)
        startService(serviceIntent)
        binding.startStopButton.text = "stop"
        binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_pause_24)
        timerStarted = true
    }
//Stop timer
    private fun stopTimer() {
        stopService(serviceIntent)
        binding.startStopButton.text = "start"
        binding.startStopButton.icon = getDrawable(R.drawable.ic_baseline_play_arrow_24)
        timerStarted = false
    }
    //added reset function
    private fun resetTimer() {
        stopTimer()
        time = 0.0
        binding.timeTV.text = getTimeStringFromDouble(time)
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            binding.timeTV.text = getTimeStringFromDouble(time)
        }
    }
    // basically breaks it to hours, minutes and secs
    private fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60
        return makeTimeString(hours, minutes, seconds)

    }
    // converts to string format
    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format("%02d:%02d:%02d", hour, min, sec)

}
