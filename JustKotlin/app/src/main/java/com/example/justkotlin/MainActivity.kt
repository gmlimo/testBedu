package com.example.justkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //View declaration to use with program
        val plusButton = findViewById<Button>(R.id.plusButton)
        val minusButton = findViewById<Button>(R.id.minusButton)
        val price_TV = findViewById<TextView>(R.id.amount_TV)
        val orderButton = findViewById<Button>(R.id.orderButton)
        var total_TV = findViewById<TextView>(R.id.total_TV)

        //Variables for the calculations
        var quantity = 0
        val price = 28.5F
        val iva = 1.08F
        var total = 0.0F

        //On ClickListener for the Plus Button
        plusButton.setOnClickListener{it
            quantity++
            price_TV.text=quantity.toString()
        }

        //On ClickListener for the Minus Button
        minusButton.setOnClickListener{it
            quantity--
            price_TV.text=quantity.toString()
        }

        //On ClickListener for the Order Button
        orderButton.setOnClickListener{ it
            total = quantity*price*iva
          total_TV.text=total.toString()
        }

    }
}