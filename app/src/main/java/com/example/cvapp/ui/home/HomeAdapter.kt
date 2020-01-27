package com.example.cvapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cvapp.data.model.CV
import com.example.cvapp.databinding.ListItemHomeCvBinding

class HomeAdapter(private val listener: OnCVClickListener) :
    ListAdapter<CV, HomeViewHolder>(object : DiffUtil.ItemCallback<CV>() {
        override fun areItemsTheSame(oldItem: CV, newItem: CV) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: CV, newItem: CV) = oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        ListItemHomeCvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

class OnCVClickListener(val clickCallback: (cvId: String) -> Unit, val deleteCallback: (cvId: String) -> Unit) {
    fun onClick(cvId: String) = clickCallback(cvId)
    fun onDelete(cvId: String) = deleteCallback(cvId)
}

class HomeViewHolder(private val binding: ListItemHomeCvBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cv: CV, listener: OnCVClickListener) {
        binding.cv = cv
        binding.listener = listener
        binding.executePendingBindings()
    }
}