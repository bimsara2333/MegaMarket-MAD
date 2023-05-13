package com.example.dashitem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dashitem.databinding.ActivityMakeorderBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Makeorder : AppCompatActivity() {

    private lateinit var binding: ActivityMakeorderBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMakeorderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.additemsbutton.setOnClickListener{
            val itemname=binding.itemname.text.toString()
            val itemsize=binding.itemsize.text.toString()
            val itemquantity=binding.itemquantiy.text.toString()
            val itemcolor=binding.itemcolor.text.toString()
            val itemaddress=binding.itemaddress.text.toString()

            val quantity = itemquantity.toDoubleOrNull()

            if(itemname.isEmpty()){
                binding.itemname.error="Item name is required"
                return@setOnClickListener
            }
            else if(itemsize.isEmpty()){
                binding.itemsize.error="Item size is required"
                return@setOnClickListener
            }

            else if(itemquantity.isEmpty()){
                binding.itemquantiy.error="Item quantity is required"
                return@setOnClickListener
            }

            else if(quantity == null){
                binding.itemquantiy.error = "Please enter a numerical value"
                return@setOnClickListener
            }

            else if(itemcolor.isEmpty()){
                binding.itemcolor.error="Item color is required"
                return@setOnClickListener
            }

            else if(itemaddress.isEmpty()){
                binding.itemaddress.error="Item Address is required"
                return@setOnClickListener
            }

            database=FirebaseDatabase.getInstance().getReference("ItemsOrderDetails")
            val Item=Item("0", itemname,itemsize,itemquantity,itemcolor,itemaddress)

            //  generate a new child node with a unique ID
            val newItemRef = database.push()

            // Get the ID of the new child node
            val newItemId = newItemRef.key

            Item.itemID = newItemId

            newItemRef.setValue(Item).addOnSuccessListener {

                binding.itemname.text.clear()
                binding.itemsize.text.clear()
                binding.itemcolor.text.clear()
                binding.itemquantiy.text.clear()
                binding.itemaddress.text.clear()

                Toast.makeText(this,"ORDER SUCCESSFULLY CREATED",Toast.LENGTH_SHORT).show()

                val intent = Intent(this, OrderDash::class.java)
                this.startActivity(intent)
            }

        }

    }
}