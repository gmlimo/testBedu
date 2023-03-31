package com.example.projectassembly

import Pastel
import Waffle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.projectassembly.clases.Coffee
import com.example.projectassembly.clases.Data
import com.example.projectassembly.clases.Donut
import com.example.projectassembly.clases.*
import com.example.projectassembly.clases.Size

const val totalD = "totalD"
const val totalC = "totalC"
const val totalW = "totalW"
const val totalP = "totalP"

class ActivityProductSel : AppCompatActivity() {

    //Variables
    val donas = Donut("Chocolate")
    val cafe = Coffee("Capuchino")
    val waffle = Waffle("chocolate")
    val pastel = Pastel("tres leches")
    var quantity = 0
    var priceD = 0.0f
    var priceC = 0.0f
    var priceW = 0.0f
    var priceP = 0.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_sel)

        //Views declarations
        val donutCheck = findViewById<CheckBox>(R.id.donutCheck)
        val coffeeCheck = findViewById<CheckBox>(R.id.coffeeCheck)
        val waffleCheck = findViewById<CheckBox>(R.id.waffleCheck)
        val pastelCheck = findViewById<CheckBox>(R.id.pastelCheck)
        val donutImage = findViewById<ImageView>(R.id.donutView)
        val plusButton = findViewById<Button>(R.id.plusButton)
        val minusButton = findViewById<Button>(R.id.minusButton)
        val irCarrito = findViewById<ImageButton>(R.id.carrito)
        val addCart = findViewById<Button>(R.id.addCart)
        val amount_TV = findViewById<TextView>(R.id.amount_TV)
        val Size = findViewById<EditText>(R.id.autoCompleteTextView)


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
                    //Get the size of the product
                    val size = Size.getText().toString()

                    //Get the subtotal to pay
                    priceD = donas.subTotal(quantity, size)
                    Toast.makeText(this, "El total es: $priceD", Toast.LENGTH_LONG).show()
                }
            }
        }
        //waffle
        waffleCheck.setOnClickListener {

            quantity = 0
            amount_TV.text="0"

            if (waffleCheck.isChecked) {
                //Toast.makeText(this, "Selección waffle", Toast.LENGTH_LONG).show()

                //Change image
                donutImage.setImageResource(R.drawable.waffle)

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
                    //Get the size of the product
                    val size = Size.getText().toString()

                    //Get the subtotal to pay
                    priceW = waffle.subTotal(quantity, size)
                    Toast.makeText(this, "El total es: $priceW", Toast.LENGTH_LONG).show()
                }
            }
        }

        //Pastel
        pastelCheck.setOnClickListener {

            quantity = 0
            amount_TV.text="0"

            if (pastelCheck.isChecked) {

                //Change image
                donutImage.setImageResource(R.drawable.pastel)

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
                    //Get the size of the product
                    val size = Size.getText().toString()

                    //Get the subtotal to pay
                    priceP = pastel.subTotal(quantity, size)
                    Toast.makeText(this, "El total es: $priceP", Toast.LENGTH_LONG).show()
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
                    //Get the size of the product
                    val size = Size.getText().toString()

                    //Get the subtotal to pay
                    priceC = cafe.subTotal(quantity, size)
                    Toast.makeText(this, "El total es: $priceC", Toast.LENGTH_LONG).show()
                }

            }
        }

        /*When the cart button is clicked the intent of the next activity is triggered and the
        prices are sent.*/
        irCarrito.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java).also {
                it.putExtra(totalD, "$priceD")
                it.putExtra(totalC, "$priceC")
                it.putExtra(totalW, "$priceW")
                it.putExtra(totalP, "$priceP")
            }
            startActivity(intent)
        }
    }
}