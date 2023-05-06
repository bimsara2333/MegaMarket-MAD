package com.example.dashitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dashitem.databinding.ActivityMakeorderBinding
import com.example.dashitem.databinding.ActivityUpdateorderBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Updateorder : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateorderBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.additemsbuttonupdate.setOnClickListener {

            val itemname = binding.updateitemname.text.toString()
            val itemsize = binding.updateitemsize.text.toString()
            val itemquantity = binding.updateitemquantiy.text.toString()
            val itemcolor = binding.updateitemcolor.text.toString()
            val itemaddress = binding.updateitemaddress.text.toString()

            if (itemname.isEmpty()) {
                binding.updateitemname.error = "Item name is required"
                return@setOnClickListener
            }
            if (itemsize.isEmpty()) {
                binding.updateitemsize.error = "Item name is required"
                return@setOnClickListener
            }
            if (itemquantity.isEmpty()) {
                binding.updateitemquantiy.error = "Item name is required"
                return@setOnClickListener
            }
            if (itemcolor.isEmpty()) {
                binding.updateitemcolor.error = "Item name is required"
                return@setOnClickListener
            }
            if (itemaddress.isEmpty()) {
                binding.updateitemaddress.error = "Item name is required"
                return@setOnClickListener
            }

            updateDataItems(itemname, itemsize, itemquantity, itemcolor, itemaddress)

        }
    }
    private fun updateDataItems(
            itemname:String,
            itemsize:String,
            itemquantity:String,
            itemcolor:String,
            itemaddress:String,

        ){
        database=FirebaseDatabase.getInstance().getReference("ItemsOrderDetails")
        val item = mapOf<String,String>(
            "itemname" to itemname,
            "itemsize" to itemsize,
            "itemquantity" to itemquantity,
            "itemcolor" to itemcolor,
            "itemaddress" to itemaddress
        )
            database.child(itemname).updateChildren(item).addOnSuccessListener {
            binding.updateitemname.text.clear()
            binding.updateitemsize.text.clear()
            binding.updateitemquantiy.text.clear()
            binding.updateitemcolor.text.clear()
            binding.updateitemaddress.text.clear()

            Toast.makeText(this,"Successfully  Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to update", Toast.LENGTH_SHORT).show()
        }

    }
}


