package com.example.sunflowerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    lateinit var button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button);

        button.setOnClickListener {
            if (button.text != "Soy Azul") {
                button.setBackgroundColor(Color.parseColor("#6495ED"));
                button.setText("Soy Azul");
            } else {
                button.setBackgroundColor(Color.parseColor("#FF0000"));
                button.setText("Soy Rojo");
            }
        }
    }
}