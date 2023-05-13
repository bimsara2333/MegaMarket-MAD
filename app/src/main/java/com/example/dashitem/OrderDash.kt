package com.example.dashitem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OrderDash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        title="Order Details Menu"

        val makebtn=findViewById<Button>(R.id.makebtn)
        makebtn.setOnClickListener{
            val intent=Intent(this,
                Makeorder::class.java)
            startActivity(intent)
        }

       val updatebtn=findViewById<Button>(R.id.updatebtn)
        updatebtn.setOnClickListener{
            val intent=Intent(this,
                Updateorder::class.java)
            startActivity(intent)
        }

        val deletebtn=findViewById<Button>(R.id.deletebtn)
        deletebtn.setOnClickListener{
            val intent=Intent(this,
                Deleteorder::class.java)
            startActivity(intent)
        }

        val seecart=findViewById<Button>(R.id.seecart)
        seecart.setOnClickListener{
            val intent=Intent(this,
                ItemsCart::class.java)
            startActivity(intent)
        }

    }
}