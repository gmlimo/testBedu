package com.example.projectassembly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projectassembly.clases.CartItem
import com.example.projectassembly.clases.Data
import com.example.projectassembly.clases.RecyclerAdapter
import com.google.android.material.snackbar.Snackbar


class CartActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {

    private lateinit var recycler: RecyclerView
    private lateinit var total: TextView
    var price1 = 0.0f
    var price2 = 0.0f
    var quantity1 = 0
    var quantity2 = 0
    var message = ""
    val datos = Data()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recycler = findViewById(R.id.cartView)
        total = findViewById(R.id.total)

        //Reads the putExtra intent from previous Activity
        price1 = (intent.getStringExtra("totalD")?.toFloat()) ?: 0.0f

        price2 = (intent.getStringExtra("totalC")?.toFloat()) ?: 0.0f

        val price3 = (intent.getStringExtra("totalW")?.toFloat()) ?: 0.0f

        val price4 = (intent.getStringExtra("totalP")?.toFloat()) ?: 0.0f

        quantity1 = (intent.getStringExtra("quantityD")?.toInt()) ?: 0

        quantity2 = (intent.getStringExtra("quantityC")?.toInt()) ?: 0

        var aux = 0


        if (price1 != 0.0f) {
            if (price2 == 0.0f) aux = 1
        }
        if (price2 != 0.0f) {
            if (price1 == 0.0f) aux = 2
        }


        when (aux) {
            1 -> recycler.adapter = RecyclerAdapter(listOf(
                CartItem(R.drawable.donas, "Donuts", quantity1, price1 )), this)
            2 -> recycler.adapter = RecyclerAdapter(listOf(
                CartItem(R.drawable.cafe, "Coffee", quantity2, price2 )), this)
            else -> recycler.adapter = RecyclerAdapter(listOf(
                CartItem(R.drawable.donas, "Donuts",quantity1, price1 ),
                CartItem(R.drawable.cafe, "Coffee", quantity2, price2 )
            ), this)
        }

        message = getString(R.string.Amount_to_pay) + "${(price1 + price2) * datos.iva}"
        total.text = message


    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this, "${position}", Toast.LENGTH_SHORT).show()
        if (position == 0) {
            Snackbar.make(total, "Remove item?", Snackbar.LENGTH_LONG).setAction("Action") {
                recycler.adapter = RecyclerAdapter(listOf(
                    CartItem(R.drawable.cafe, "Coffee", quantity2, price2 )), this)
                message = getString(R.string.Amount_to_pay) + "${(price2) * datos.iva}"
                total.text = message
            }
                .setBackgroundTint(getColor(R.color.teal_200))
                .setActionTextColor(getColor(R.color.white))
                .show()
            recycler.adapter?.notifyItemChanged(position)
        }
        if (position == 1) {
            Snackbar.make(total, "Remove item?", Snackbar.LENGTH_LONG).setAction("Action") {
                recycler.adapter = RecyclerAdapter(listOf(
                    CartItem(R.drawable.donas, "Donuts", quantity1, price1 )), this)
                message = getString(R.string.Amount_to_pay) + "${(price1) * datos.iva}"
                total.text = message
            }
                .setBackgroundTint(getColor(R.color.teal_200))
                .setActionTextColor(getColor(R.color.white))
                .show()
            recycler.adapter?.notifyItemChanged(position)
        }
    }
}