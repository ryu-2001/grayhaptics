package com.example.gray_haptics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //各画像ボタンの取得
        val b1 = findViewById<ImageButton>(R.id.border_1)
        val b2 = findViewById<ImageButton>(R.id.border_2)
        val b3 = findViewById<ImageButton>(R.id.border_3)
        val b4 = findViewById<ImageButton>(R.id.border_4)
        val b5 = findViewById<ImageButton>(R.id.border_5)
        val b6 = findViewById<ImageButton>(R.id.border_6)

        //ボタンを押したら各レイアウトに移動
        b1.setOnClickListener {
            val intent = Intent(this,Sub1::class.java)
            startActivity(intent)
        }
        b2.setOnClickListener {
            val intent = Intent(this,Sub2::class.java)
            startActivity(intent)
        }
        b3.setOnClickListener {
            val intent = Intent(this,Sub3::class.java)
            startActivity(intent)
        }
        b4.setOnClickListener {
            val intent = Intent(this,Sub4::class.java)
            startActivity(intent)
        }
        b5.setOnClickListener {
            val intent = Intent(this,Sub5::class.java)
            startActivity(intent)
        }
        b6.setOnClickListener {
            val intent = Intent(this,Sub6::class.java)
            startActivity(intent)
        }
    }
}