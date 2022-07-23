package com.example.timeman

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.timeman.R
import com.example.timeman.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var items: ArrayList<String>? = null
    private var itemsAdapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null

    override fun onCreate(SavedInstanceState: Bundle?) {
        super.onCreate(SavedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        binding.button.setOnClickListener { view -> addItem(view) }
        items = ArrayList()
        itemsAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            items!!
        )
        listView?.adapter = itemsAdapter
        setupListViewListener()
    }

    private fun setupListViewListener() {
        listView!!.onItemLongClickListener =
            OnItemLongClickListener { parent: AdapterView<*>?, view: View?, i: Int, l: Long ->
                val context = applicationContext
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show()
                items!!.removeAt(i)
                itemsAdapter!!.notifyDataSetChanged()
                true
            }
    }

    private fun addItem(view: View) {
        val input = findViewById<EditText>(R.id.editText2)
        val itemText = input.text.toString()
        if (itemText != "") {
            itemsAdapter!!.add(itemText)
            input.setText("")
        } else {
            Toast.makeText(applicationContext, "PLease enter text...", Toast.LENGTH_LONG).show()
        }
    }
}


