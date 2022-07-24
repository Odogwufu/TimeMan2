package com.example.timeman

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.timeman.databinding.ActivityMain3Binding
import java.util.*

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }

    fun AddCalendarEvent(view:View) {
        val calendarEvent = Calendar.getInstance()
        val i = Intent(Intent.ACTION_EDIT)
        i.type = "vnd.android.cursor.item/event"
        i.putExtra("beginTime", calendarEvent.timeInMillis)
        i.putExtra("allDay", true)
        i.putExtra("rule", "FREQ=YEARLY")
        i.putExtra("endTime", calendarEvent.timeInMillis + 60 * 60 * 1000)
        i.putExtra("title", "Calendar Event")
        startActivity(i)
    }


}