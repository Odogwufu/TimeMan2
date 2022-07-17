package com.example.timeman

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

class TimerService: Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        const val TIMER_UPDATED="timerUpdated"
        const val TIME_EXTRA="timeExtra"

    }

    // create timer variable
    private val timer = Timer()

    //starts service
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        val time = intent.getDoubleExtra(TIME_EXTRA, 0.0)
        timer.scheduleAtFixedRate(TimeTask(time), 0, 1000)
        return START_NOT_STICKY

    }

    //onDestroy function ends/terminates program
    override fun onDestroy() {

        timer.cancel()
        super.onDestroy()

    }
//inner class timertask  updates timer
    private inner class TimeTask(private var time: Double) : TimerTask() {

        override fun run() {
            val intent = Intent(TIMER_UPDATED)
            time++
            intent.putExtra(TIME_EXTRA, time)
            sendBroadcast(intent)
        }

    }

}

