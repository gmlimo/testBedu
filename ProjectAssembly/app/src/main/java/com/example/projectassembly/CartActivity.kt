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

        //Display the amount to pay
        val datos = Data()
        Toast.makeText(this, "El total es: ${(price1+price2) * datos.iva}", Toast.LENGTH_LONG).show()

        //Declares de array to place the objects (items to purchase)
        val items: ArrayList<CartItem> = ArrayList<CartItem>()

        //Depends of the selection the ArrayList is adjusted
        if (price1 != 0.0f) {
            if (price2 != 0.0f) {
                items.add(0, CartItem(R.drawable.donas, "Donas", price1))
                items.add(1, CartItem(R.drawable.cafe, "Café", price2))
            } else items.add(0, CartItem(R.drawable.donas, "Donas", price1))
        } else items.add(0, CartItem(R.drawable.cafe, "Café", price2))

        //Use the itemAdapter to display the ArrayList
        val itemAdapter: ItemAdapter = ItemAdapter(this, items)
        val listView: ListView = findViewById(R.id.cartView)
        listView.setAdapter(itemAdapter)
    }

}