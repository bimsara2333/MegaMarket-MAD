package com.example.megamarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.megamarket.Models.Card
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddCard : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var nameEditText: EditText
    private lateinit var numberEditText: EditText
    private lateinit var cvvEditText: EditText
    private lateinit var dateEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        // Get a reference to the Firebase "Cards" node
        dbref = FirebaseDatabase.getInstance().getReference("Cards")

        // Get references to the EditText views
        nameEditText = findViewById(R.id.name)
        numberEditText = findViewById(R.id.number)
        cvvEditText = findViewById(R.id.cvv)
        dateEditText = findViewById(R.id.date)

        // Get a reference to the "Add Card" button and set a click listener on it
        val addButton = findViewById<Button>(R.id.addcard)
        addButton.setOnClickListener {
            // Call the addCard function when the button is clicked
            Log.d("AddCard", "addCard function called")
            addCard()
        }
    }

    private fun addCard() {
        // Get the text from the EditText views
        val name = nameEditText.text.toString()
        val number = numberEditText.text.toString()
        val cvv = cvvEditText.text.toString()
        val date = dateEditText.text.toString()

        if (name.isNotEmpty() && number.isNotEmpty() && cvv.isNotEmpty() && date.isNotEmpty()) {
            // If all the fields are filled in, create a new Card object and push it to Firebase
            val cardID = dbref.push().key!!
            val card = Card(cardID, name, number, cvv, date)

            dbref.child(cardID).setValue(card).addOnCompleteListener { task ->
                // If the data is successfully inserted, clear the input data and show a success message
                if (task.isSuccessful) {
                    nameEditText.setText("")
                    numberEditText.setText("")
                    cvvEditText.setText("")
                    dateEditText.setText("")
                    Toast.makeText(this, "Data inserted Successfully", Toast.LENGTH_LONG).show()


                    val intent = Intent(this, FullViewCard::class.java)
                    intent.putExtra("id", cardID)
                    intent.putExtra("name",name)
                    intent.putExtra("number",number)
                    intent.putExtra("cvv",cvv)
                    intent.putExtra("date",date)
                    startActivity(intent)

                } else {
                    // If there is an error, show an error message
                    Toast.makeText(this, "Error ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            // If any of the fields are empty, show a message asking the user to fill them in
            if (name.isEmpty()) {
                Toast.makeText(this, "Please re-fill name", Toast.LENGTH_LONG).show()
            }
            if (number.isEmpty()) {
                Toast.makeText(this, "Please re-fill number", Toast.LENGTH_LONG).show()
            }
            if (cvv.isEmpty()) {
                Toast.makeText(this, "Please re-fill cvv", Toast.LENGTH_LONG).show()
            }
            if (date.isEmpty()) {
                Toast.makeText(this, "Please re-fill date", Toast.LENGTH_LONG).show()
            }
        }
    }
}
