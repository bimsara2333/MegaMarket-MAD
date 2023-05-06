package com.example.testapp_for_assiment.Activities

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.testapp_for_assiment.Models.ProductModel
import com.example.testapp_for_assiment.R
import com.google.firebase.database.*

class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        //var EId=findViewById<TextView>(R.id.eId)

        //get the passed id
        var id = intent.getStringExtra("proId").toString()

        //EId.setText(id).toString()

        var cat=findViewById<TextView>(R.id.icat)
        var name=findViewById<TextView>(R.id.iname)
        var quant=findViewById<TextView>(R.id.iquan)
        var desc=findViewById<TextView>(R.id.idesc)
        var price=findViewById<TextView>(R.id.iprice)
        var update=findViewById<Button>(R.id.update)
        var delete=findViewById<Button>(R.id.delete)

         database=FirebaseDatabase.getInstance().getReference("Products")

        database.child("Products").child(id).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(ProductModel::class.java)

                name.setText(user?.proName).toString()
                cat.setText(user?.proCat).toString()
                price.setText(user?.proprice).toString()

                println(user?.proName.toString())
                println(user?.proCat.toString())
                println(user?.proDesc.toString())
                println(user?.proprice.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "Failed to read value.", error.toException())
            }


        })

        update.setOnClickListener{
            val intent=Intent(this, UpdateDataActivity::class.java)
            startActivity(intent)

            val intent1=Intent(this, UpdateDataActivity::class.java)
            intent1.putExtra("empId",id)
            startActivity(intent1)

        }

        delete.setOnClickListener {
            DataDelete(id)

            val intent=Intent(this,DataFetch::class.java)
            startActivity(intent)
        }

        }
    private fun DataDelete(id: String) {
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (allEmpData in snapshot.children){
                    val empId = allEmpData.child("empId").getValue(String::class.java)
                    if (empId == id){
                        allEmpData.ref.removeValue()
                        Toast.makeText(this@EmployeeDetailsActivity,"Deleted Successfully", Toast.LENGTH_SHORT).show()


                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "Failed to delete value.", error.toException())
            }
        })
    }

    }





