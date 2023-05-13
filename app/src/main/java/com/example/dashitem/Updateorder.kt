package com.example.dashitem

import android.content.Intent
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

        val itemId = intent.getStringExtra("itemId")
        val name = intent.getStringExtra("name")
        val size = intent.getStringExtra("size")
        val qty = intent.getStringExtra("qty")
        val color = intent.getStringExtra("color")
        val address = intent.getStringExtra("address")

        binding.updateitemname.setText(name)
        binding.updateitemsize.setText(size)
        binding.updateitemquantiy.setText(qty)
        binding.updateitemcolor.setText(color)
        binding.updateitemaddress.setText(address)

        binding.additemsbuttonupdate.setOnClickListener {

            val itemname = binding.updateitemname.text.toString()
            val itemsize = binding.updateitemsize.text.toString()
            val itemquantity = binding.updateitemquantiy.text.toString()
            val itemcolor = binding.updateitemcolor.text.toString()
            val itemaddress = binding.updateitemaddress.text.toString()

            val quantity = itemquantity.toDoubleOrNull()

            if (itemname.isEmpty()) {
                binding.updateitemname.error = "Item name is required"
                return@setOnClickListener
            }
            else if (itemsize.isEmpty()) {
                binding.updateitemsize.error = "Item name is required"
                return@setOnClickListener
            }

            else if (itemquantity.isEmpty()) {
                binding.updateitemquantiy.error = "Item name is required"
                return@setOnClickListener
            }

            else if (quantity == null) {
                binding.updateitemquantiy.error = "Please enter a numerical value"
                return@setOnClickListener
            }

            else if (itemcolor.isEmpty()) {
                binding.updateitemcolor.error = "Item name is required"
                return@setOnClickListener
            }
            else if (itemaddress.isEmpty()) {
                binding.updateitemaddress.error = "Item name is required"
                return@setOnClickListener
            }

            if (itemId != null) {
                updateDataItems(itemId, itemname, itemsize, itemquantity, itemcolor, itemaddress)
            }

        }

        binding.deleteBtn.setOnClickListener {
            if (itemId != null) {
                deleteItemById(itemId)
            }
        }

    }
    private fun updateDataItems(
            itemId:String,
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
        if (itemId != null) {
            database.child(itemId).updateChildren(item).addOnSuccessListener {
                binding.updateitemname.text.clear()
                binding.updateitemsize.text.clear()
                binding.updateitemquantiy.text.clear()
                binding.updateitemcolor.text.clear()
                binding.updateitemaddress.text.clear()

                Toast.makeText(this,"Successfully  Updated", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, OrderDash::class.java)
                this.startActivity(intent)

            }.addOnFailureListener{
                Toast.makeText(this,"Failed to update", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteItemById(itemId: String) {
        val database = FirebaseDatabase.getInstance().getReference("ItemsOrderDetails")
        database.child(itemId).removeValue()
            .addOnSuccessListener {
               Toast.makeText(this,"Successfully  Delete", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, OrderDash::class.java)
                this.startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this,"Failed to Delete", Toast.LENGTH_SHORT).show()
            }
    }
}


