package com.example.mad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class edituser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edituser)

        val auth=FirebaseAuth.getInstance().currentUser
        val name=findViewById<EditText>(R.id.editname)
        val email=findViewById<EditText>(R.id.editemail)
        val pass=findViewById<EditText>(R.id.editpass)
        val saveBtn=findViewById<Button>(R.id.savebtn)

        saveBtn.setOnClickListener {

            val Pass=pass.text.toString()

            val Name=name.text.toString()

            val Email=email.text.toString()

            auth?.updateEmail(Email)?.addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Successupdate Email",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"Error update Email",Toast.LENGTH_LONG).show()
                }
            }

            auth?.updatePassword(Pass)?.addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"Success update Pass",Toast.LENGTH_LONG).show()
                }
                else
                {
                    Toast.makeText(this,"Error supdate Psss",Toast.LENGTH_LONG).show()
                }
            }

        }

    }
}