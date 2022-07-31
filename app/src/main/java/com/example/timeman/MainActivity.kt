package com.example.timeman

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
// initialize variables
    private lateinit var items: ArrayList<String>
    private lateinit var itemsAdapter: ArrayAdapter<String>
    private lateinit var button: Button
    private lateinit var listView:ListView



    override fun onCreate(SavedInstanceState: Bundle?) {
        super.onCreate(SavedInstanceState)
        setContentView(R.layout.activity_main)
//created button
        val button3 =findViewById<Button>(R.id.button3)
        button3.setOnClickListener{
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
//finding xml data from activity.xml files
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
    }

    // holding click on an item removes it.
    private fun setupListViewListener() {
        listView.setOnItemLongClickListener{parent:AdapterView<*>?,view:View?,i,l ->

            val context : Context = applicationContext
            Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show()
            items.removeAt(i)
            itemsAdapter.notifyDataSetChanged()
            true
        }
    }
//adds new item
    private fun addItem(view:View) {
        val input = findViewById<EditText>(R.id.editText2)
        val itemText = input.text.toString()
        if (itemText != "") {
            itemsAdapter.add(itemText)
            input.setText("")
        } else {
            Toast.makeText(applicationContext, "PLease enter text...", Toast.LENGTH_LONG).show()
        }
    }
}