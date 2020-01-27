package com.example.cvapp.ui.add

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cvapp.data.model.WorkExperience
import com.example.cvapp.databinding.ListItemAddWorkExperienceBinding

class AddAdapter(private val listener: OnDataCorrectlyFieldedListener) :
    ListAdapter<WorkExperience, AddViewHolder>(object : DiffUtil.ItemCallback<WorkExperience>() {
        override fun areItemsTheSame(oldItem: WorkExperience, newItem: WorkExperience) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: WorkExperience, newItem: WorkExperience) =
            oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AddViewHolder(
        ListItemAddWorkExperienceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        listener
    )

    override fun onBindViewHolder(holder: AddViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AddViewHolder(
    private val binding: ListItemAddWorkExperienceBinding,
    private val listener: OnDataCorrectlyFieldedListener
) :
    RecyclerView.ViewHolder(binding.root) {

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.invalidateAll()
            binding.workExperience?.areAllDataFielded()?.let {
                listener.onDataCorrectlyFieldedListener()
            }
        }
    }

    fun bind(workExperience: WorkExperience) {
        binding.workExperience = workExperience
        binding.textWatcher = textWatcher
        binding.executePendingBindings()
    }
}

class OnDataCorrectlyFieldedListener(private val callback: () -> Unit) {
    fun onDataCorrectlyFieldedListener() = callback()
}