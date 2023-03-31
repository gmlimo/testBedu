package com.example.projectassembly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.projectassembly.clases.CartItem
import com.example.projectassembly.clases.Data
import com.example.projectassembly.clases.ItemAdapter


class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //Reads the putExtra intent from previous Activity
        val price1 = (intent.getStringExtra("totalD")?.toFloat()) ?: 0.0f

        val price2 = (intent.getStringExtra("totalC")?.toFloat()) ?: 0.0f

        val price3 = (intent.getStringExtra("totalW")?.toFloat()) ?: 0.0f

        val price4 = (intent.getStringExtra("totalP")?.toFloat()) ?: 0.0f

        //Display the amount to pay
        val datos = Data()
        Toast.makeText(
            this,
            "El total es: ${(price1 + price2 + price3 + price4) * datos.iva}",
            Toast.LENGTH_LONG
        ).show()

        //Declares de array to place the objects (items to purchase)
        val items: ArrayList<CartItem> = ArrayList<CartItem>()


        if (price1 != 0.0f) {
            items.add(CartItem(R.drawable.donas, "Donas", price1))
        }
        if (price2 != 0.0f) {
            items.add(CartItem(R.drawable.cafe, "Café", price2))
        }
        if (price3 != 0.0f) {
            items.add(CartItem(R.drawable.waffle, "Waffle", price3))
        }
        if (price4 != 0.0f) {
            items.add(CartItem(R.drawable.pastel, "Pastel", price4))
        }

        //Use the itemAdapter to display the ArrayList
        val itemAdapter: ItemAdapter = ItemAdapter(this, items)
        val listView: ListView = findViewById(R.id.cartView)
        listView.setAdapter(itemAdapter)


    }
}