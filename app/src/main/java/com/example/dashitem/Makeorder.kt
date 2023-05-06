package com.example.dashitem

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

            if(itemname.isEmpty()){
                binding.itemname.error="Item name is required"
                return@setOnClickListener
            }
            if(itemsize.isEmpty()){
                binding.itemsize.error="Item size is required"
                return@setOnClickListener
            }
            if(itemquantity.isEmpty()){
                binding.itemquantiy.error="Item quantity is required"
                return@setOnClickListener
            }
            if(itemcolor.isEmpty()){
                binding.itemcolor.error="Item color is required"
                return@setOnClickListener
            }
            if(itemaddress.isEmpty()){
                binding.itemaddress.error="Item Address is required"
                return@setOnClickListener
            }



            database=FirebaseDatabase.getInstance().getReference("ItemsOrderDetails")
            val Item=Item(itemname,itemsize,itemquantity,itemcolor,itemaddress)

            database.child(itemname).setValue(Item).addOnSuccessListener {

                binding.itemname.text.clear()
                binding.itemsize.text.clear()
                binding.itemcolor.text.clear()
                binding.itemquantiy.text.clear()
                binding.itemaddress.text.clear()

                Toast.makeText(this,"ORDER SUCCESSFULLY CREATED",Toast.LENGTH_SHORT).show()

            }



        }

    }
}