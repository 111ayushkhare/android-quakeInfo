package com.example.quakeinfo.screens.quakedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.quakeinfo.R

/**
 * A simple [Fragment] subclass.
 * Use the [QuakeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuakeDetailFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quake_detail, container, false)
    }
}