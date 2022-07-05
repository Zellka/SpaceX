package com.example.spacex.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacex.presentation.view.DetailFragment.Companion.LAUNCH
import com.example.spacex.R
import com.example.spacex.databinding.FragmentLaunchesBinding
import com.example.launch.domain.models.Launch
import com.example.spacex.app.App
import com.example.spacex.presentation.adapter.LaunchesAdapter
import com.example.spacex.presentation.viewmodel.LaunchesViewModel
import com.example.spacex.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

class LaunchesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentLaunchesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LaunchesViewModel
    private lateinit var adapter: LaunchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(LaunchesViewModel::class.java)
        _binding = FragmentLaunchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        binding.progress.visibility = View.VISIBLE
        getLaunches()
    }

    private fun setupUI() {
        binding.rvLaunches.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = LaunchesAdapter { launch: Launch -> showLaunch(launch) }
        adapter.setLaunches(arrayListOf())
        binding.rvLaunches.adapter = adapter
    }

    private fun getLaunches() {
        viewModel.getLaunches().observe(this.viewLifecycleOwner,
            {
                adapter.setLaunches(it.docs)
                binding.progress.visibility = View.GONE
            })
    }

    private fun showLaunch(launch: Launch) {
        val bundle = bundleOf(LAUNCH to launch)
        findNavController().navigate(
            R.id.action_launchesFragment_to_detailFragment,
            bundle
        )
    }
}
