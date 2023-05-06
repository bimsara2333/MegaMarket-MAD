package com.example.megamarket

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.megamarket.Models.Card
import com.example.megamarket.R
import com.google.firebase.database.*

class CardUpdate : AppCompatActivity() {

    private lateinit var database:DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_update)

        database = FirebaseDatabase.getInstance().reference

        val name=findViewById<EditText>(R.id.name)
        val number=findViewById<EditText>(R.id.number)

        val date=findViewById<EditText>(R.id.date)
        val updateBtn=findViewById<Button>(R.id.update)

        var id = intent.getStringExtra("id").toString()

        database.child("Cards").child(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(Card::class.java)

                name.setText(user?.name).toString()
                number.setText(user?.number).toString()

                date.setText(user?.date).toString()

                println(user?.name.toString())
                println(user?.number.toString())

                println(user?.date.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(ContentValues.TAG, "Failed to read value.", error.toException())
            }
        })

        updateBtn.setOnClickListener{
            val newName=name.text.toString()
            val newNumber=number.text.toString()

            val newDate=date.text.toString()

            updateDetail(newName,newNumber,newDate)
        }

    }

    fun updateDetail(name: String, number:String, date:String){
        val updates= mapOf<String,String>(
            "name" to name,
            "number" to number,
            "date" to date)

        var id = intent.getStringExtra("cardID").toString()

        database.child("Cards").child(id).updateChildren(updates)
            .addOnSuccessListener {
                Toast.makeText(this,"update details Success",Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener{
                Toast.makeText(this,"Details Update Unsuccess",Toast.LENGTH_LONG).show()
            }
    }


}