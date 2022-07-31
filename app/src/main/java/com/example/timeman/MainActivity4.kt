package com.example.timeman

import com.example.timeman.R
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.timeman.dto.Profile
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity4 : AppCompatActivity() {


    private lateinit var username: String

    private lateinit var profiledes: String

    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(com.example.timeman.R.layout.activity_main4)

        db = FirebaseFirestore.getInstance()

        username = findViewById<EditText>(R.id.editTextTextPersonName2).toString()
        profiledes = findViewById<EditText>(R.id.editTextTextPersonName3).toString()

        val submitBtn = findViewById<Button>(R.id.Submit)

        submitBtn.setOnClickListener {


                username = findViewById<EditText>(R.id.editTextTextPersonName2).getText().toString()
                profiledes = findViewById<EditText>(R.id.editTextTextPersonName3).getText().toString()

                    addDataToFirestore(username!!, profiledes)

            }
        }


    private fun addDataToFirestore(
        username: String,
        profiledes: String?,

    ) {


        val dbProfile: CollectionReference = db!!.collection("Profile")

        // adding our data to our courses object class.
        val profile = Profile(username, profiledes)

        // below method is use to add data to Firebase Firestore.
        dbProfile.add(profile)
            .addOnSuccessListener(OnSuccessListener<Any?> { // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(
                    this@MainActivity4,
                    "Your Course has been added to Firebase Firestore",
                    Toast.LENGTH_SHORT
                ).show()
            })
            .addOnFailureListener(OnFailureListener { e -> // this method is called when the data addition process is failed.
                Toast.makeText(this@MainActivity4, "Fail to add course \n$e", Toast.LENGTH_SHORT)
                    .show()
            })
    }

}
