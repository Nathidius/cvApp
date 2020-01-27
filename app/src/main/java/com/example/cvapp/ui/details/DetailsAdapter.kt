package com.example.cvapp.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cvapp.data.model.WorkExperience
import com.example.cvapp.databinding.ListItemDetailsWorkExperienceBinding

class DetailsAdapter : ListAdapter<WorkExperience, DetailsViewHolder>(object :
    DiffUtil.ItemCallback<WorkExperience>() {
    override fun areItemsTheSame(oldItem: WorkExperience, newItem: WorkExperience) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: WorkExperience, newItem: WorkExperience) =
        oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetailsViewHolder(
        ListItemDetailsWorkExperienceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DetailsViewHolder(private val binding: ListItemDetailsWorkExperienceBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(workExperience: WorkExperience) {
        binding.workExperience = workExperience
        binding.executePendingBindings()
    }
}