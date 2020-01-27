package com.example.cvapp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.cvapp.R
import com.example.cvapp.databinding.FragmentAddCvBinding
import com.example.cvapp.ui.utils.VerticalDividerItemDecoration
import com.example.cvapp.ui.utils.hideKeyboard

class AddFragment : Fragment() {

    private lateinit var viewModel: AddFragmentViewModel
    lateinit var binding: FragmentAddCvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_cv, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupObservers()
    }

    private fun setupView() {
        with(binding.workExperienceListRecyclerView) {
            adapter = AddAdapter(OnDataCorrectlyFieldedListener {
                viewModel.validateData()
            })
            addItemDecoration(VerticalDividerItemDecoration(context, R.drawable.list_item_divider))
        }
    }

    private fun setupObservers() {
        viewModel.shouldNavigateBack.observe(viewLifecycleOwner, Observer {
            if (it) {
                view?.let { view -> activity?.hideKeyboard(view) }
                val bundle = bundleOf("wasCVAdded" to true)
                findNavController().navigate(R.id.action_addCVFragment_to_homeFragment, bundle)
            }
        })
    }

}