package com.example.dashitem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ItemsCart : AppCompatActivity() {

    private lateinit var dbref:DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_cart)

        userRecyclerView=findViewById(R.id.userList)
        userRecyclerView.layoutManager=LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList= arrayListOf<Item>()
        getItemData()

    }

    private fun getItemData() {
        dbref=FirebaseDatabase.getInstance().getReference("ItemsOrderDetails")
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for (userSnapshot in snapshot.children){

                        val item=userSnapshot.getValue(Item::class.java)
                        userArrayList.add(item!!)

                    }

                    userRecyclerView.adapter=MyAdapter(userArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}