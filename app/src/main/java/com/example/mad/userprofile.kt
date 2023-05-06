package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mad.Models.BuyerModel
import com.google.firebase.database.*

class userprofile : AppCompatActivity() {

    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)

        dbRef = FirebaseDatabase.getInstance().getReference("Buyers")

        val id = intent.getStringExtra("id").toString()


        println("id id id id id ")
        println(id)
        
        var name=findViewById<TextView>(R.id.viewName)
        var email=findViewById<TextView>(R.id.viewEmail)
        var addr=findViewById<TextView>(R.id.viewAdd)
        var editpro=findViewById<Button>(R.id.editBtn)

        editpro.setOnClickListener {
            val intent=Intent(this,edituser::class.java)
            startActivity(intent)
        }

        dbRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                // This method is called once with the initial value and whenever data at this location is updated.
                val value = dataSnapshot.getValue(BuyerModel::class.java)
                
                name.setText(value?.cusName.toString())
                email.setText(value?.email.toString())
                addr.setText(value?.cusID.toString())
                
                Toast.makeText(this@userprofile,"Data show success",Toast.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

    }
}