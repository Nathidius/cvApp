package com.example.cvapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.cvapp.R
import com.example.cvapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeFragmentViewModel

    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        viewModel.getAllCV()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        arguments?.getBoolean("wasCVAdded")?.let {
            if (it) {
                viewModel.getAllCV()
                arguments?.remove("wasCVAdded")
            }
        }
    }

    private fun setupView() {
        with(binding.cvListRecyclerView) {
            adapter = HomeAdapter(OnCVClickListener({
                val bundle = bundleOf("cvId" to it)
                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
            }, {
                viewModel.deleteCV(it)
            }))
        }
    }

    private fun setupListeners() {
        with(binding.addCVButton) {
            setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addCVFragment)
            }
        }
    }

}