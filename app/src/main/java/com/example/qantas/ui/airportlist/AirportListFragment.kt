package com.example.qantas.ui.airportlist

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qantas.R
import com.example.qantas.databinding.FragmentAirportListBinding
import com.example.qantas.model.Airport
import com.example.qantas.ui.adapter.AirportItemAdapter
import com.example.qantas.ui.adapter.ClickInterface
import com.example.qantas.utils.Constants.*
import com.example.qantas.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment to show list of airports
 */
@AndroidEntryPoint
class AirportListFragment : Fragment() {

    private var binding: FragmentAirportListBinding? = null
    private var viewModel: AirportsViewModel? = null
    private val adapter = AirportItemAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAirportListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AirportsViewModel::class.java)
        setUpView()
        observeResponse()
        getAirportList()
    }

    private fun setUpView() {
        binding?.progressBar?.show()
        binding?.appBar?.toolbarTitle?.text = getString(R.string.app_name)
        binding?.appBar?.imgBack?.visibility = View.GONE

        binding?.airportListView?.adapter = adapter
        binding?.airportListView?.apply {
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        }
        adapter.setItemClick(object : ClickInterface<Airport> {
            override fun onClick(data: Airport) {
                navigateToDetailScreen(data)
            }
        })
    }


    private fun getAirportList() {
        if (NetworkUtils.hasNetwork(this.requireContext()) == true) {
            viewModel?.getAirports()
        } else {
            Toast.makeText(requireContext(), getString(R.string.no_internet), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun observeResponse() {
        viewModel?.airportsListLiveData?.observe(viewLifecycleOwner) {
            if (it != null) {
                setUpAirportList(it)
                binding?.progressBar?.hide()
            }
        }
        viewModel?.errorLiveData?.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                showErrorDialog(it)
            }
        }
    }

    private fun showErrorDialog(error: String) {
        AlertDialog.Builder(context)
            .setTitle(getString(R.string.error))
            .setMessage(error)
            .setNegativeButton(getString(R.string.ok_button), null)
            .create().show()
    }

    private fun setUpAirportList(airports: List<Airport>) {
        adapter.updateFlight(airports)
    }

    private fun navigateToDetailScreen(airport: Airport) {
        val airportData = Airport(
            airportCode = airport.airportCode,
            airportName = airport.airportName,
            city = airport.city,
            country = airport.country,
            domesticAirport = airport.domesticAirport,
            eticketableAirport = airport.eticketableAirport,
            internationalAirport = airport.internationalAirport,
            location = airport.location,
            onlineIndicator = airport.onlineIndicator,
            region = airport.region,
            regionalAirport = airport.regionalAirport,
            state = airport.state
        )
        val action = AirportListFragmentDirections.actionAirportListFragmentToAirportDetailsScreen(airportData)
        findNavController(requireParentFragment()).navigate(
           action
        )
    }
}