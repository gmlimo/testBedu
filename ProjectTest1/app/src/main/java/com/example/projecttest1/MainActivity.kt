package com.example.projecttest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

enum class Size {Small, Medium, Jumbo}
const val totalD = "totalD"
const val totalC = "totalC"

class MainActivity : AppCompatActivity() {

    //Variables
    val costoCafe = 42.5f
    val costoDonas = 12.4f
    var quantity = 0
    var priceD = 0.0f
    var priceC = 0.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Views declarations
        val donutCheck = findViewById<CheckBox>(R.id.donutCheck)
        val coffeeCheck = findViewById<CheckBox>(R.id.coffeeCheck)
        val donutImage = findViewById<ImageView>(R.id.donutView)
        val plusButton = findViewById<Button>(R.id.plusButton)
        val minusButton = findViewById<Button>(R.id.minusButton)
        val irCarrito = findViewById<ImageButton>(R.id.carrito)
        val addCart = findViewById<Button>(R.id.addCart)
        val amount_TV = findViewById<TextView>(R.id.amount_TV)
        val Size2 = findViewById<EditText>(R.id.autoCompleteTextView)

       // val intent = Intent(this, CartActivity::class.java)


        //If there is a change in Donas selection do this...
        donutCheck.setOnClickListener {
            quantity = 0
            amount_TV.text="0"
            if (donutCheck.isChecked) {
                //Toast.makeText(this, "Selección Donas", Toast.LENGTH_LONG).show()

                //Change image
                donutImage.setImageResource(R.drawable.donas)

                //On ClickListener for the Plus Button
                plusButton.setOnClickListener {
                    it
                    quantity++
                    amount_TV.text = quantity.toString()
                }

                //On ClickListener for the Minus Button
                minusButton.setOnClickListener {
                    it
                    quantity--
                    amount_TV.text = quantity.toString()
                }


                addCart.setOnClickListener {
                    priceD = quantity * costoDonas
                    Toast.makeText(this, "El total es: $priceD", Toast.LENGTH_LONG).show()
                }
            } //else Toast.makeText(this, "No hay productos seleccionados", Toast.LENGTH_SHORT).show()
        }

        //If there is a change in Café selection do this...
        coffeeCheck.setOnClickListener {
            quantity = 0
            amount_TV.text="0"
            if (coffeeCheck.isChecked) {
                //Toast.makeText(this, "Selección Café", Toast.LENGTH_LONG).show()

                //Change image
                donutImage.setImageResource(R.drawable.cafe)

                //On ClickListener for the Plus Button
                plusButton.setOnClickListener {
                    it
                    quantity++
                    amount_TV.text = quantity.toString()
                }

                //On ClickListener for the Minus Button
                minusButton.setOnClickListener {
                    it
                    quantity--
                    amount_TV.text = quantity.toString()
                }


                addCart.setOnClickListener {
                    priceC = quantity * costoCafe
                    Toast.makeText(this, "El total es: $priceC", Toast.LENGTH_LONG).show()
                }

            }// else Toast.makeText(this, "No hay productos seleccionados", Toast.LENGTH_SHORT).show()
        }

        //When the cart button is clicked the intent of the next activity is triggered
        irCarrito.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java).also {
                it.putExtra(totalD, "$priceD")
                it.putExtra(totalC, "$priceC")
            }
            startActivity(intent)
        }
        /*     val selection = when {
                 donutCheck.isChecked -> 1
                 coffeeCheck.isChecked -> 2
                 else -> 0
             }

             when (selection) {
                 1 -> {
                     Toast.makeText(this, "Selección Donas", Toast.LENGTH_LONG).show()

                     //On ClickListener for the Plus Button
                     plusButton.setOnClickListener{it
                         quantity++
                         amount_TV.text=quantity.toString()
                     }

                     //On ClickListener for the Minus Button
                     minusButton.setOnClickListener{it
                         quantity--
                         amount_TV.text=quantity.toString()
                     }


                     addCart.setOnClickListener{
                         price = quantity * costoDonas
                         Toast.makeText(this, "El total es: $price", Toast.LENGTH_LONG).show()
                 }
             }
                 2 -> {
                     Toast.makeText(this, "Selección Café", Toast.LENGTH_LONG).show()
                     // amount_TV.text="0"
                     //On ClickListener for the Plus Button
                     plusButton.setOnClickListener{it
                         quantity++
                         amount_TV.text=quantity.toString()
                     }

                     //On ClickListener for the Minus Button
                     minusButton.setOnClickListener{it
                         quantity--
                         amount_TV.text=quantity.toString()
                     }


                     addCart.setOnClickListener{
                         price = quantity * costoCafe
                         Toast.makeText(this, "El total es: $price", Toast.LENGTH_LONG).show()
                     }
                 }
                 else -> Toast.makeText(this, "No hay productos seleccionados", Toast.LENGTH_SHORT).show()
             }*/


        /*   //On ClickListener for the Plus Button
        plusButton.setOnClickListener{it
            quantity++
            amount_TV.text=quantity.toString()
        }

        //On ClickListener for the Minus Button
        minusButton.setOnClickListener{it
            quantity--
            amount_TV.text=quantity.toString()
        }

        addCart.setOnClickListener{
            Toast.makeText(this, "El total es: $price", Toast.LENGTH_LONG).show()
        }
        */

    }
}
