package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mad.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class userlogin : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginbtn.setOnClickListener {

            val email=binding.signemail.text.toString()
            val pass=binding.enterpw.text.toString()

            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"Login Sucsess", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, Homepage::class.java)
                    startActivity(intent)

                }else{
                    Toast.makeText(this,"Login Fail"+it.exception.toString(),Toast.LENGTH_SHORT).show()
                }
            }

        }




    }
}