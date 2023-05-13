package com.example.megamarket

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Pair
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.megamarket.Adapter.CardAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // hide status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_add_card)
/*
        // initialize variables
        val topAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        val image: ImageView = findViewById(R.id.imageView3)
        val logo: TextView = findViewById(R.id.textView)
        val slogan: TextView = findViewById(R.id.textView2)

        // set animations
        image.animation = topAnim
        logo.animation = bottomAnim
        slogan.animation = bottomAnim

        // set delay for splash screen
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, seller_login::class.java)

            val pairs = arrayOf(
                Pair<View, String>(image, "logo image"),
                Pair<View, String>(logo, "logo text")
            )

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this@MainActivity,
                    *pairs
                )
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }
        }, 3000) // replace SPLASH_SCREEN with delay in milliseconds
*/

    }
}
