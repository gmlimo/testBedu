package edu.upbc.beducoffee.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import edu.upbc.beducoffee.R
import edu.upbc.beducoffee.databinding.FragmentCartBinding
import edu.upbc.beducoffee.files.CartItem
import edu.upbc.beducoffee.files.RecyclerAdapter

private lateinit var total: TextView
private lateinit var recycler: RecyclerView

class CartFragment : Fragment(R.layout.fragment_cart), RecyclerAdapter.OnItemClickListener {

    //Declaramos binding y recycler usado para mostrar elementos del carrito
    private var fragmentBlankBinding: FragmentCartBinding? = null

    private lateinit var items: MutableList<CartItem>
    private lateinit var mAdapater: RecyclerAdapter

    //Variables para obtener información de CardFragment
    var price1 = 0.0f
    var price2 = 0.0f
    var quantity1 = 0
    var quantity2 = 0
    var message = ""


    //Aquí se declara fragment de "colocar el pedido"
    val fragmentP = MakePurchaseFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCartBinding.bind(view)
        fragmentBlankBinding = binding

        total = binding.total
        recycler = binding.cartView

        //Se obtiene la información de la selección de productos
        price1 = arguments?.getFloat("PRICE_D") ?: 0.0f
        quantity1 = arguments?.getInt("QUANTITY1") ?: 0
        price2 = arguments?.getFloat("PRICE_C") ?: 0.0f
        quantity2 = arguments?.getInt("QUANTITY2") ?: 0

        setCart()
        setUpRecyclerView(items)

        //Mensaje final del total de la compra
        message = getString(R.string.Amount_to_pay) + "${(price1 + price2) * 1.08f}"
        binding.total.text = message

        //Aquí nos vamos al fragment de "colocar pedido"
        binding.button.setOnClickListener {
            changeFragment()
        }

    }

    private fun setCart() {
        var aux = 0

        //Lógica de manejo de contenido del carrito
        if (price1 != 0.0f) {
            if (price2 == 0.0f) aux = 1
        }
        else if (price2 != 0.0f) {
            if (price1 == 0.0f) aux = 2
        }
        else aux = 0

        when (aux) {
            1 -> items = mutableListOf(CartItem(R.drawable.donas, getString(R.string.donuts), quantity1, price1))
            2 -> items = mutableListOf(CartItem(R.drawable.cafe, getString(R.string.coffee), quantity2, price2))
            else -> items = mutableListOf(CartItem(R.drawable.donas, getString(R.string.donuts), quantity1, price1),
                CartItem(R.drawable.cafe, getString(R.string.coffee), quantity2, price2))
        }
    }

    private fun setUpRecyclerView(mItems: MutableList<CartItem>){
        recycler.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CartFragment?.requireContext())
            mAdapater = RecyclerAdapter(items, this@CartFragment)
            adapter = mAdapater
        }
    }


    //Lógica para eliminar elementos del carrito
    override fun onItemClick(position: Int) {
        //Toast.makeText(this, "${position}", Toast.LENGTH_SHORT).show()
        if (position == 0) {
            Snackbar.make(total, getString(R.string.remove_item), Snackbar.LENGTH_LONG).setAction(getString(
                            R.string.remove)) {
                items.removeAt(position)
                mAdapater.notifyItemRemoved(position)
                setUpRecyclerView(items)
                if (items.size != 0) {
                    message = getString(R.string.Amount_to_pay) + "${price2 * 1.08f}"
                    total.text = message
                }
                else {
                    message = getString(R.string.Amount_to_pay) + "0.0"
                    total.text = message
                }
            }
                .setBackgroundTint(getColor(requireContext(), R.color.colorPrimary))
                .setActionTextColor(getColor(requireContext(), R.color.white))
                .show()
        }
        if (position == 1) {
            Snackbar.make(total, getString(R.string.remove_item), Snackbar.LENGTH_LONG).setAction(getString(
                R.string.remove)) {
                items.removeAt(position)
                mAdapater.notifyItemRemoved(position)
                setUpRecyclerView(items)
                if (items.size != 0) {
                    message = getString(R.string.Amount_to_pay) + "${price1 * 1.08f}"
                    total.text = message
                }
                else {
                    message = getString(R.string.Amount_to_pay) + "0.0"
                    total.text = message
                }
            }
                .setBackgroundTint(getColor(requireContext(), R.color.colorPrimary))
                .setActionTextColor(getColor(requireContext(), R.color.white))
                .show()
        }
    }


    //Lógica para cambiar de fragment
    private fun changeFragment() {
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView, fragmentP)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }
}