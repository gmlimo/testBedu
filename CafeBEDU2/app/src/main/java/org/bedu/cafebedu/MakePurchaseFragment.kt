package org.bedu.cafebedu

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.bedu.cafebedu.databinding.FragmentMakePurchaseBinding


class MakePurchaseFragment : Fragment(R.layout.fragment_make_purchase) {

    private var fragmentBlankBinding: FragmentMakePurchaseBinding ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMakePurchaseBinding.bind(view)
        fragmentBlankBinding = binding

        val latitude = preferences.getFloat(LATITUDE_KEY, 0.0f)
        val longitud = preferences.getFloat(LONGITUD_KEY, 0.0f)

        binding.position.text = "Your current position is: ${latitude} , ${longitud}"

        val quantity1 = arguments?.getInt("QUANTITY1") ?: 0
        val quantity2 = arguments?.getInt("QUANTITY2") ?: 0

        val order = "${quantity1} donuts and ${quantity2} coffees"

        val bundle = Bundle().apply {
            putString("ORDER", order)
        }

        binding.purchaseButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)

                val intent = Intent("org.bedu.receiver.DATA_TRANSFER")
                intent.putExtras(bundle)
                requireActivity().sendBroadcast(intent)
            }
        }
    }


}