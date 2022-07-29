package com.example.timeman

import android.content.Context
import android.content.Intent
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
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null


    override fun onCreate(SavedInstanceState: Bundle?) {
        super.onCreate(SavedInstanceState)
        setContentView(R.layout.activity_main)

        val button3 =findViewById<Button>(R.id.button3)
        button3.setOnClickListener{
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
        databaseReference = firebaseDatabase!!.getReference("EmployeeInfo");
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

    private fun addItem(view:View) {
        val input = findViewById<EditText>(R.id.editText2)
        val itemText = input.text.toString()
        if (itemText != "") {
            itemsAdapter.add(itemText)
            input.setText("")
            addDatatoFirebase(itemText);
        } else {
            Toast.makeText(applicationContext, "PLease enter text...", Toast.LENGTH_LONG).show()
        }
    }
    private fun addDatatoFirebase(itemText: String) {
        // below 3 lines of code is used to set
        // data in our object class.
        items.add(itemText)


        // we are use add value event listener method
        // which is called with database reference.
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
}