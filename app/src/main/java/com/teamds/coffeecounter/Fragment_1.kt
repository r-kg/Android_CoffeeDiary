package com.teamds.coffeecounter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_1.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment_1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val fab : View = view.findViewById(R.id.frag_1_fab)

        fab.setOnClickListener { view ->
            val intent = Intent(this@Fragment_1.context, AddActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.slide_in,R.anim.slide_out)
        }


        return view
    }



}
