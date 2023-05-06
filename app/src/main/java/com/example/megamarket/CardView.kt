package com.example.megamarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.megamarket.Adapter.CardAdapter
import com.example.megamarket.Models.Card
import com.example.megamarket.R
import com.google.firebase.database.*


class CardView : AppCompatActivity() {

    private lateinit var carddetails:RecyclerView
    private lateinit var cardList:ArrayList<Card>
    private lateinit var dbRef:DatabaseReference
    private lateinit var btnadd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)




        carddetails=findViewById(R.id.carddetails)
        carddetails.layoutManager=LinearLayoutManager(this,)
        carddetails.setHasFixedSize(true)
        cardList= arrayListOf<Card>()

        getCardData()

        btnadd=findViewById(R.id.button2)

        btnadd.setOnClickListener{
            val intent=Intent(this, AddCard::class.java)
            startActivity(intent)
        }
    }

    private fun getCardData(){
        carddetails.visibility= View.GONE

        dbRef=FirebaseDatabase.getInstance().getReference("Cards")

        dbRef.addValueEventListener(object:ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                cardList.clear()
                if(snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val cardData=empSnap.getValue(Card::class.java)
                        cardList.add(cardData!!)
                    }
                    val mAdapter=CardAdapter(cardList)

                    mAdapter.setOnItemClickListener(object:CardAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@CardView, FullViewCard::class.java)
                            //put extras
                            intent.putExtra("id",cardList[position].cardID)
                            intent.putExtra("name",cardList[position].name)
                            intent.putExtra("number",cardList[position].number)
                            intent.putExtra("cvv",cardList[position].cvv)
                            intent.putExtra("date",cardList[position].date)
                            startActivity(intent)
                        }
                    })

                    carddetails.adapter=mAdapter
                    carddetails.visibility=View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }

        })
    }
}