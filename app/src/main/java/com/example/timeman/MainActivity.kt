package com.example.timeman

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private lateinit var items: ArrayList<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private lateinit var button: Button
    private lateinit var listView:ListView

    //Database
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    //Notification
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val channelId = "12345"
    private val description = "Test"


    override fun onCreate(SavedInstanceState: Bundle?) {
        super.onCreate(SavedInstanceState)
        setContentView(R.layout.activity_main)

        // Notification
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val button3 =findViewById<Button>(R.id.button3)
        button3.setOnClickListener{
            val intent = Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }
        val button6 =findViewById<Button>(R.id.button6)
        button6.setOnClickListener{
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        listView =findViewById(R.id.listView)
        button = findViewById(R.id.button)

        button.setOnClickListener(this::addItem)
        items = ArrayList()
        itemsAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            items
        )
        listView.adapter =itemsAdapter
        setupListViewListener()
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase!!.getReference("ToDoList");
    }

    private fun setupListViewListener() {
        listView.setOnItemLongClickListener{parent:AdapterView<*>?,view:View?,i,l ->

            val context : Context = applicationContext
            Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show()
            items.removeAt(i)
            itemsAdapter.notifyDataSetChanged()
            true
        }
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    fun addItem(view:View) {
        val input = findViewById<EditText>(R.id.editText2)
        val itemText = input.text.toString()
        if (itemText != "") {
            itemsAdapter.add(itemText)
            input.setText("")
            addDatatoFirebase(itemText);

            val switch=findViewById<Switch>(R.id.switch1)
            if ( switch.isChecked){
                createNotification(itemText)
            }

        } else {
            Toast.makeText(applicationContext, "PLease enter text...", Toast.LENGTH_LONG).show()
        }
    }



    private fun addDatatoFirebase(itemText: String) {

        // we are use add value event listener method
        //  called with database reference.
            databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference!!.setValue(items)

                // after adding this data we are showing toast message.
                Toast.makeText(this@MainActivity, "data added", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(this@MainActivity, "Fail to add data $error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


    fun createNotification(itemText: String){ val intent = Intent(this, LauncherActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
            notificationChannel.lightColor = Color.BLUE
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId).setContentTitle("Item Added to To-do List "
                    ).setContentText(itemText).setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(
                BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher)).setContentIntent(pendingIntent)
        }
        notificationManager.notify(12345, builder.build())
    }


      fun fetchItems(): ArrayList<String> {

            return items
        }

}