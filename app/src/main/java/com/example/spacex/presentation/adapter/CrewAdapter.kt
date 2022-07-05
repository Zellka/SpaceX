package com.example.spacex.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.launch.domain.models.Crew
import com.example.spacex.databinding.ItemCrewBinding

class CrewAdapter() : RecyclerView.Adapter<CrewAdapter.CrewHolder>() {

    private var crew = mutableListOf<Crew>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrewHolder {
        val binding =
            ItemCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = CrewHolder(binding)
        return viewHolder
    }

    override fun getItemCount() = crew.size

    override fun onBindViewHolder(holder: CrewHolder, position: Int) {
        holder.bind(crew[position])
    }

    inner class CrewHolder(var binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(crew: Crew) {
            binding.imageCrew.load(crew.image)
            binding.nameCrew.text = crew.name
            binding.agencyCrew.text = crew.agency
            binding.statusCrew.text = crew.status
        }
    }

    fun setCrew(newData: List<Crew>) {
        //crew.clear()
        crew.addAll(newData)
        notifyDataSetChanged()
    }
}