package com.example.spacex.presentation.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.spacex.R
import com.example.launch.domain.models.Crew
import com.example.spacex.databinding.FragmentDetailBinding
import com.example.launch.domain.models.Launch
import com.example.spacex.app.App
import com.example.spacex.presentation.adapter.CrewAdapter
import com.example.spacex.presentation.utils.NetworkUtil
import com.example.spacex.presentation.viewmodel.LaunchesViewModel
import com.example.spacex.presentation.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: LaunchesViewModel
    private lateinit var adapter: CrewAdapter
    private var launch: Launch? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
        setHasOptionsMenu(true)
        arguments?.let {
            launch = it.getSerializable(LAUNCH) as Launch
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        setupViewModel()
        setupRecyclerView()
        binding.progress.visibility = View.VISIBLE
        if (NetworkUtil.checkInternet(this.requireActivity())) {
            getCrew()
        } else {
            Toast.makeText(this.requireContext(), "Отсутствует интернет", Toast.LENGTH_SHORT).show()
        }
        setupUI()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(LaunchesViewModel::class.java)
    }

    private fun setupRecyclerView() {
        binding.rvCrew.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = CrewAdapter()
        adapter.setCrew(arrayListOf())
        binding.rvCrew.adapter = adapter
    }

    private fun getCrew() {
        viewModel.getCrew().observe(this.viewLifecycleOwner,
            {
                selectCrew(it)
                binding.progress.visibility = View.GONE
            })
    }

    private fun setupUI() {
        if (launch?.links?.patch?.large != null) {
            binding.imageLaunch.load(launch?.links?.patch?.large)
        } else {
            binding.imageLaunch.load(R.drawable.holder)
        }
        binding.nameLaunch.text = launch?.name
        if (launch!!.success) {
            binding.statusLaunch.setTextColor(Color.GREEN)
            binding.statusLaunch.setText(R.string.status_done)
        } else {
            binding.statusLaunch.setTextColor(Color.RED)
            binding.statusLaunch.setText(R.string.status_process)
        }
        val simpleDateFormat = SimpleDateFormat("HH:MM dd-MM-yyyy")
        val date: String = simpleDateFormat.format(launch?.date_utc)
        binding.dateLaunch.text = date
        binding.detailLaunch.text = launch?.details
        binding.flightLaunch.text = "Number of flights: " + launch?.cores?.get(0)?.flight.toString()
    }

    private fun selectCrew(crew: List<Crew>) {
        val list: ArrayList<Crew> = arrayListOf()
        for (i in crew) {
            for (j in i.launches) {
                if (j == launch?.id) {
                    list.add(i)
                }
            }
        }
        adapter.setCrew(list)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_detailFragment_to_launchesFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val LAUNCH = "LAUNCH"
    }
}

