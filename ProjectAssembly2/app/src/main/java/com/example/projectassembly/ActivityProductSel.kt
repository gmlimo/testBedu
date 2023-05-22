package com.example.projectassembly

import Pastel
import Waffle
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.projectassembly.clases.Coffee
import com.example.projectassembly.clases.Donut
import com.example.projectassembly.databinding.ActivityProductSelBinding

const val totalD = "totalD"
const val totalC = "totalC"
const val totalW = "totalW"
const val totalP = "totalP"
const val quantityD = "quantityD"
const val quantityC = "quantityC"

class ActivityProductSel : AppCompatActivity() {

    //Views declaration
    private lateinit var binding: ActivityProductSelBinding

    //Options for the Spinners
    var size = arrayOf(" ", "Small", "Medium", "Jumbo")

    //Product classes initialization
    val donas = Donut("Chocolate")
    val cafe = Coffee("Capuchino")
    val waffle = Waffle("chocolate")
    val pastel = Pastel("tres leches")

    //Variables to increment or decrement the quantity of our products
    var quantity1 = 0
    var quantity2 = 0
    var size_Selection1 = " "
    var size_Selection2 = " "

    //Variables to calculate the subtotal of the purchase
    var priceD = 0.0f
    var priceC = 0.0f
    var priceW = 0.0f
    var priceP = 0.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_sel)

        //Links with the views
        binding = ActivityProductSelBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //On ClickListener for the Plus Button for Donuts
        binding.donutPlus.setOnClickListener {
            it
            quantity1++
            //Set quantity upper limit
            if (quantity1 > 50) {
                quantity1 = 50
                Toast.makeText(this, "The quantity can´t be more than 50", Toast.LENGTH_SHORT).show()
            }
            //Update the donuts quantity
            binding.donutQuantity.text = quantity1.toString()
        }

        //On ClickListener for the Minus Button for Donuts
        binding.donutMinus.setOnClickListener {
            it
            quantity1--
            //Set quantity lower limit
            if (quantity1 < 0) {
                quantity1 = 0
                Toast.makeText(this, "The quantity can´t be less than 0", Toast.LENGTH_SHORT).show()
            }
            //Update the donuts quantity
            binding.donutQuantity.text = quantity1.toString()
        }

        //On ClickListener for the Plus Button for Coffee
        binding.coffeePlus.setOnClickListener {
            it
            quantity2++
            //Set quantity upper limit
            if (quantity2 > 50) {
                quantity2 = 50
                Toast.makeText(this, "The quantity can´t be more than 50", Toast.LENGTH_SHORT).show()
            }
            //Update the donuts quantity
            binding.coffeeQuantity.text = quantity2.toString()
        }

        //On ClickListener for the Minus Button for Coffee
        binding.coffeeMinus.setOnClickListener {
            it
            quantity2--
            //Set quantity lower limit
            if (quantity2 < 0) {
                quantity2 = 0
                Toast.makeText(this, "The quantity can´t be less than 0", Toast.LENGTH_SHORT).show()
            }
            //Update the donuts quantity
            binding.coffeeQuantity.text = quantity2.toString()
        }

        //Agregamos Adapter para el click de items
        binding.donutSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //Pass the selection to variable
                size_Selection1 = size[position]
                //Get the subtotal to pay
                priceD = donas.subTotal(quantity1, size_Selection1)
               // Toast.makeText(this@ActivityProductSel, "${priceD}", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                showDialog(
                    "No seleccionaste el tamaño",
                    "Vuelve a desplegar la lista y asegúrate de elegir correctamente a alguna"
                )
                Toast.makeText(applicationContext, "No hay selección", Toast.LENGTH_LONG).show()
            }
        }
        //Adaptador para mostrar elementos del menú del Spinner
        val menuAdapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, size)
        binding.donutSize.adapter = menuAdapter1


        binding.coffeeSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //Pass the selection to variable
                size_Selection2 = size[position]
                //Get the subtotal to pay
                priceC = cafe.subTotal(quantity2, size_Selection2)
                //Toast.makeText(this@ActivityProductSel, "${priceC}", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                showDialog(
                    "No seleccionaste el tamaño",
                    "Vuelve a desplegar la lista y asegúrate de elegir correctamente a alguna"
                )
                Toast.makeText(applicationContext, "No hay selección", Toast.LENGTH_LONG).show()
            }
        }
        //Adaptador para mostrar elementos del menú del Spinner
        val menuAdapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, size)
        binding.coffeeSize.adapter = menuAdapter2

        /*When the cart button is clicked the intent of the next activity is triggered and the
        prices are sent.*/
        binding.carrito.setOnClickListener {

                val intent = Intent(this, CartActivity::class.java).also {
                    it.putExtra(totalD, "$priceD")
                    it.putExtra(totalC, "$priceC")
                    it.putExtra(quantityD, "$quantity1")
                    it.putExtra(quantityC, "$quantity2")
                    //it.putExtra(totalW, "$priceW")
                    //it.putExtra(totalP, "$priceP")
                }
                startActivity(intent)
            }
    }


    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialogInterface, which -> dialogInterface.dismiss() }
            .create()
            .show()
    }

    }
