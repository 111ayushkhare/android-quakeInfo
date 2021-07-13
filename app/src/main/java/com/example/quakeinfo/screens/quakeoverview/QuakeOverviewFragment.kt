package com.example.quakeinfo.screens.quakeoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quakeinfo.R
import com.example.quakeinfo.databinding.FragmentQuakeOverviewBinding

/**
 * A simple [Fragment] subclass.
 * Use the [QuakeOverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuakeOverviewFragment : Fragment() {

    private val overviewViewModel by lazy {
        ViewModelProvider(this).get(QuakeOverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentQuakeOverviewBinding>(
            inflater, R.layout.fragment_quake_overview, container, false
        )

        binding.lifecycleOwner = this
        binding.overviewViewModel = overviewViewModel

        binding.magnitudeSlider.addOnChangeListener { slider, value, fromUser ->
            overviewViewModel.minQuakeMagnitude = slider.values[0].toDouble()
            overviewViewModel.maxQuakeMagnitude = slider.values[1].toDouble()
        }

        val adapter = QuakeOverviewRecyclerViewAdapter(this)
        binding.overviewRv.adapter = adapter

        binding.fetchInfoBtn.setOnClickListener { view: View ->
            overviewViewModel.getQuakeInfo()
        }

        overviewViewModel.quakeResponseFeatures.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                adapter.updateFeaturesList(it)
            }
        })

        return binding.root
    }
}