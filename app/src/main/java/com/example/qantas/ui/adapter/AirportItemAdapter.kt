package com.example.qantas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qantas.databinding.AirportListItemBinding
import com.example.qantas.model.Airport

/**
 * This Adapter is to show list of folders
 */
class AirportItemAdapter : RecyclerView.Adapter<AirportItemAdapter.AirportItemViewHolder>() {

    var airportList = mutableListOf<Airport>()
    private var clickInterface: ClickInterface<Airport>? = null

    /**
     * This is create view holder method used by adapter
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportItemViewHolder {
        val binding =
            AirportListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirportItemViewHolder(binding)

    }

    /**
     * Bind view holder
     */
    override fun onBindViewHolder(holder: AirportItemViewHolder, position: Int) {
        airportList.let {
            if (it.isNotEmpty()) {
                val airport = airportList.get(position)
                holder.bind(airport)

            }
        }
    }

    /**
     * Get Items count
     */
    override fun getItemCount(): Int {
        return airportList?.size ?: 0
    }

    fun setItemClick(clickInterface: ClickInterface<Airport>) {
        this.clickInterface = clickInterface
    }

    fun updateFlight(flights: List<Airport>) {
        this.airportList = flights.toMutableList()
        notifyDataSetChanged()
    }

    /**
     * This is a view holder class for timeline metadata item (other than heading)
     * @param binding
     */
    inner class AirportItemViewHolder(private val binding: AirportListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Bind method used to bind data with view
         */
        fun bind(airport: Airport) {
            binding.airportName.text = airport.airportName
            binding.countryName.text = airport.country.countryName
            binding.root.setOnClickListener { clickInterface?.onClick(airport) }

        }

    }

}

interface ClickInterface<T> {
    fun onClick(data: T)
}