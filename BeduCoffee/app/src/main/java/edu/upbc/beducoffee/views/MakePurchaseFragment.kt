package edu.upbc.beducoffee.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.upbc.beducoffee.R
import edu.upbc.beducoffee.databinding.FragmentMakePurchaseBinding
import edu.upbc.beducoffee.utils.executeOrRequestPermission
import edu.upbc.beducoffee.utils.simpleNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MakePurchaseFragment : Fragment(R.layout.fragment_make_purchase) {

    private var fragmentBlankBinding: FragmentMakePurchaseBinding?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Se inicializa binding
        val binding = FragmentMakePurchaseBinding.bind(view)
        fragmentBlankBinding = binding

        //Variables que obtienen la ubicación actual generada desde la actividad de ProductSel
        val latitude = preferences.getFloat(LATITUDE_KEY, 0.0f)
        val longitud = preferences.getFloat(LONGITUD_KEY, 0.0f)

        binding.radioCash.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.radioCard.isChecked = false }

        binding.radioCard.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.radioCash.isChecked = false}

        //Se despliega la información del gps
        binding.position.text = "Your current position is: ${latitude} , ${longitud}"

        //Se obtiene el resumen de la compra
        val quantity1 = arguments?.getInt("QUANTITY1") ?: 0
        val quantity2 = arguments?.getInt("QUANTITY2") ?: 0

        //Se genera el mensaje para ser enviado
        val order = "${quantity1} donuts and ${quantity2} coffees"
        val bundle = Bundle().apply {
            putString("ORDER", order)
        }

        //Por medio de un Broadcast se manda nuestro pedido al presionar el botón
        binding.purchaseButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)

                executeOrRequestPermission(this@MakePurchaseFragment?.requireContext() as Activity) {
                    simpleNotification(this@MakePurchaseFragment?.requireContext() as Activity)
                }

                val intent = Intent("org.bedu.receiver.DATA_TRANSFER")
                intent.putExtras(bundle)
                requireActivity().sendBroadcast(intent)
            }
        }
    }


}