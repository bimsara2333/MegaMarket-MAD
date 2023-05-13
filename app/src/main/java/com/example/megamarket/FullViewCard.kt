package com.example.megamarket

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class FullViewCard : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var btnadd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_view_card)

        database=FirebaseDatabase.getInstance().getReference("Cards")

        var EId=findViewById<TextView>(R.id.cardID)

        btnadd=findViewById(R.id.floatingActionButton14)

        btnadd.setOnClickListener{
            val intent=Intent(this, CardView::class.java)
            startActivity(intent)
        }

        //get the passed id
        var id = intent.getStringExtra("cardID").toString()

        println("Your id ...................................................")
        print("Your id is - "+id)

        EId.setText(id).toString()

        var cardName=findViewById<TextView>(R.id.hName)
        var num=findViewById<TextView>(R.id.cNum)
        var cvv=findViewById<TextView>(R.id.cvv)
        var date=findViewById<TextView>(R.id.date)
        var cardid=findViewById<TextView>(R.id.cardID)
        var update=findViewById<Button>(R.id.update)
        var delete=findViewById<Button>(R.id.delete)

        //database=FirebaseDatabase.getInstance().getReference("Cards")


        val Id =intent.getStringExtra("id")
        val Name =intent.getStringExtra("name")
        val Cvv =intent.getStringExtra("cvv")
        val Date =intent.getStringExtra("date")
        val Number =intent.getStringExtra("number")

        cardid.setText(Id).toString()
        cardName.setText(Name).toString()
        cvv.setText(Cvv).toString()
        date.setText(Date).toString()
        num.setText(Number).toString()



        // database.child("Cards").child(id).addListenerForSingleValueEvent(object :
        // ValueEventListener {
        // override fun onDataChange(dataSnapshot: DataSnapshot) {
        //  val user = dataSnapshot.getValue(Card::class.java)

        /* cardName.setText(user?.name).toString()
            num.setText(user?.number).toString()
            cvv.setText(user?.cvv).toString()
            date.setText(user?.date).toString() */

        /*  println(user?.cardID.toString())
            println(user?.name.toString())
            println(user?.number.toString())
            println(user?.cvv.toString())
            println(user?.date.toString()) */

        //  }

        // override fun onCancelled(error: DatabaseError) {
        //  Log.d(TAG, "Failed to read value.", error.toException())
        //  }


        // })

        update.setOnClickListener{
            val intent=Intent(this, CardUpdate::class.java)
            startActivity(intent)

            val intent1=Intent(this, CardUpdate::class.java)
            intent1.putExtra("id",Id)
            startActivity(intent1)

        }

        delete.setOnClickListener {
            DataDelete(Id.toString())
            val intent=Intent(this,CardView::class.java)
            startActivity(intent)
        }

    }

    fun DataDelete(id:String){

        database.child(id).removeValue().addOnCompleteListener {
            Toast.makeText(this@FullViewCard,"Data delete success",Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this@FullViewCard,"Data delete Faild",Toast.LENGTH_LONG).show()
        }
    }

}
