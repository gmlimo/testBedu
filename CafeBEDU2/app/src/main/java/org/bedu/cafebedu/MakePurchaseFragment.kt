package org.bedu.cafebedu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    }


}