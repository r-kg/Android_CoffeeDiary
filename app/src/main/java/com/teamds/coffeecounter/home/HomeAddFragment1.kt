package com.teamds.coffeecounter.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamds.coffeecounter.R


/**
 * A simple [Fragment] subclass.
 * Use the [HomeAddFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeAddFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_add1, container, false)
    }


}