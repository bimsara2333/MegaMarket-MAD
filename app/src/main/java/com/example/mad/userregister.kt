package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mad.Models.BuyerModel
import com.example.mad.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class userregister : AppCompatActivity() {

    /*private lateinit var userName: EditText
    private lateinit var userEmail: EditText
    private lateinit var userPass: EditText
    private lateinit var btnRegister: Button*/

    private lateinit var dbref: DatabaseReference
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        dbref = FirebaseDatabase.getInstance().getReference("Buyers")

        binding.registerBtn.setOnClickListener {
            val email = binding.enteremail.text.toString()
            val password = binding.enterpw.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Google auth successs", Toast.LENGTH_LONG).show()

                            val userName = findViewById<EditText>(R.id.entername)
                            val uName = userName.text.toString()

                            val buyID = dbref.push().key!!
                            val buyer = BuyerModel(buyID, uName,email)


                            dbref.child(buyID).setValue(buyer).addOnCompleteListener {
                                Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()
                                val intent = Intent(this, userprofile::class.java)
                                intent.putExtra("id", buyID)
                                startActivity(intent)
                            }.addOnFailureListener { err ->
                                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                            }


                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }


    }
}