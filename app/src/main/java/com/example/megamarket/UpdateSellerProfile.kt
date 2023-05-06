package com.example.megamarket


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class UpdateSellerProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.UpdateSellerProfile)

        val auth=FirebaseAuth.getInstance().currentUser


        val name=findViewById<EditText>(R.id.name)
        val num=findViewById<EditText>(R.id.num)
        val pass=findViewById<EditText>(R.id.password)
        val saveBtn=findViewById<Button>(R.id.regi)






        saveBtn.setOnClickListener {

            val Pass=pass.text.toString()

            val name=name.text.toString()

            val Num=num.text.toString()

            auth?.updateNum(name)?.addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Success update Number",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this,"Error update Number",Toast.LENGTH_LONG).show()
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