package com.example.dashitem

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.dashitem.databinding.ActivityDeleteorderBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Deleteorder : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteorderBinding
    private lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityDeleteorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deletebutton.setOnClickListener{
            var deletesearch=binding.deletesearch.text.toString()

            if(deletesearch.isNotEmpty())
                deletedata(deletesearch)
            else
                Toast.makeText(this, "please enter the ITEM Name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deletedata(deletesearch: String) {

        database=FirebaseDatabase.getInstance().getReference("ItemsOrderDetails")
        database.child(deletesearch).removeValue().addOnSuccessListener {

            binding.deletesearch.text.clear()
            Toast.makeText(this,"ITEM Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"item Failed",Toast.LENGTH_SHORT).show()
        }

    }
}