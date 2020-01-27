package com.example.cvapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.cvapp.R
import com.example.cvapp.databinding.FragmentDetailsBinding
import com.example.cvapp.ui.utils.VerticalDividerItemDecoration

class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsFragmentViewModel
    lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailsFragmentViewModel::class.java)
        arguments?.getString("cvId")?.let {
            viewModel.getCVDetails(it)
        } ?: run {
            findNavController().navigateUp()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        with(binding.workExperienceListRecyclerView) {
            adapter = DetailsAdapter()
            addItemDecoration(VerticalDividerItemDecoration(context, R.drawable.list_item_divider))
        }
    }
}