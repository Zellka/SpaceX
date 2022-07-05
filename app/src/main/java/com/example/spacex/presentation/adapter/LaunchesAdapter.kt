package com.example.spacex.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.databinding.ItemLaunchBinding
import coil.load
import com.example.launch.domain.models.Launch
import com.example.spacex.R
import java.text.SimpleDateFormat

class LaunchesAdapter(private var listener: (Launch) -> Unit) :
    RecyclerView.Adapter<LaunchesAdapter.LaunchesHolder>() {

    private var launches = mutableListOf<Launch>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LaunchesHolder {
        val binding =
            ItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = LaunchesHolder(binding)
        viewHolder.itemView.setOnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION)
                listener(launches[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = launches.size

    override fun onBindViewHolder(holder: LaunchesHolder, position: Int) {
        holder.bind(launches[position])
    }

    inner class LaunchesHolder(var binding: ItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(launch: Launch) {
            if (launch.links.patch.small != null) {
                binding.imageLaunch.load(launch.links.patch.small)
            } else {
                binding.imageLaunch.load(R.drawable.holder)
            }
            binding.nameLaunch.text = launch.name
            if (launch.success) {
                binding.statusLaunch.setTextColor(Color.GREEN)
                binding.statusLaunch.setText(R.string.status_done)
            } else {
                binding.statusLaunch.setTextColor(Color.RED)
                binding.statusLaunch.setText(R.string.status_process)
            }
            binding.flightLaunch.text = "Number of flights: " + launch.cores[0].flight.toString()
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
            val date: String = simpleDateFormat.format(launch.date_utc)
            binding.dateLaunch.text = date
        }
    }

    fun setLaunches(newData: List<Launch>) {
        launches.addAll(newData)
        notifyDataSetChanged()
    }
}