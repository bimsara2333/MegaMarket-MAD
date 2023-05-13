package com.example.megamarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.megamarket.Models.SellerModel
import com.example.megamarket.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
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


        dbref = FirebaseDatabase.getInstance().getReference("Users")

        binding.regi.setOnClickListener {

            val sellerName = binding.username1.text.toString()
            val password = binding.password1.text.toString()


            if (sellerName.isNotEmpty() && password.isNotEmpty()) {

                firebaseAuth.createUserWithEmailAndPassword(sellerName,password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Google auth successs", Toast.LENGTH_LONG).show()

                        val userName = findViewById<EditText>(R.id.username)
                        val sName = userName.text.toString()

                        val userId=firebaseAuth.currentUser
                        val buyer = SellerModel(userId.toString(), sName,sellerName)


                        dbref.child(userId.toString()).setValue(buyer).addOnCompleteListener {
                            Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, SellerProfile::class.java)
                            intent.putExtra("id", userId)
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