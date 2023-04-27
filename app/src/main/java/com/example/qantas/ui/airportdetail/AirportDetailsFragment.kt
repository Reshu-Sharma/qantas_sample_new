package com.example.qantas.ui.airportdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.qantas.R
import com.example.qantas.databinding.FragmentAirportDetailsBinding
import com.example.qantas.model.Airport
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for Airport details
 */
@AndroidEntryPoint
class AirportDetailsFragment : Fragment() {

    private var binding:FragmentAirportDetailsBinding ?= null
    private var viewModel: AirportDetailViewModel? = null
    private val args: AirportDetailsFragmentArgs by navArgs<AirportDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAirportDetailsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AirportDetailViewModel::class.java)
        viewModel?.prepareDetailScreenData(args.airport)
        setUpClickListener()
        observe()
    }

    private fun setUpClickListener(){
        binding?.button?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.appBar?.imgBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun observe() {
        viewModel?.airportNameLiveData?.observe(viewLifecycleOwner){
            binding?.airport = getString(R.string.airport_name) + it
            binding?.appBar?.toolbarTitle?.text = it
        }
        viewModel?.countryNameLiveData?.observe(viewLifecycleOwner){
            binding?.country = getString(R.string.country_name) + it
        }
        viewModel?.timeZoneLiveData?.observe(viewLifecycleOwner){
            binding?.timezone = getString(R.string.timezone) + it
        }
        viewModel?.addressLiveData?.observe(viewLifecycleOwner){
            binding?.address = getString(R.string.address) + it
        }

    }
}