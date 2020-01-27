package com.example.cvapp.ui.utils

import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cvapp.R
import com.example.cvapp.data.model.CV
import com.example.cvapp.data.model.WorkExperience
import com.example.cvapp.ui.add.AddAdapter
import com.example.cvapp.ui.details.DetailsAdapter
import com.example.cvapp.ui.home.HomeAdapter
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

@BindingAdapter("homeListData")
fun bindHomeRecyclerView(recyclerView: RecyclerView, homeListData: List<CV>?) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(homeListData)
}

@BindingAdapter("homeListData")
fun bindHomeProgressBar(frameLayout: FrameLayout, homeListData: List<CV>?) {
    frameLayout.visibility = if (homeListData.isNullOrEmpty()) View.VISIBLE else View.GONE
}

@BindingAdapter("detailsWorkExperienceListData")
fun bindDetailsRecyclerView(
    recyclerView: RecyclerView,
    detailsWorkExperienceListData: List<WorkExperience>?
) {
    val adapter = recyclerView.adapter as DetailsAdapter
    adapter.submitList(detailsWorkExperienceListData)
}

@BindingAdapter("addWorkExperienceListData")
fun bindAddRecyclerView(
    recyclerView: RecyclerView,
    addWorkExperienceListData: List<WorkExperience>?
) {
    val adapter = recyclerView.adapter as AddAdapter
    adapter.submitList(addWorkExperienceListData)
}

@BindingAdapter("photo")
fun bindPhoto(imageView: ImageView, photoUrl: String?) {
    photoUrl?.let {
        Picasso.get()
            .load(if (it.isEmpty()) null else it)
            .placeholder(R.drawable.ic_cv_image)
            .error(R.drawable.ic_cv_image_error)
            .into(imageView)
    }
}

@BindingAdapter("name", "surname")
fun bindingName(textView: TextView, name: String?, surname: String?) {
    ifLet(name, surname) { (name, surname) ->
        textView.text = textView.context.getString(R.string.name, name, surname)
    }
}

@BindingAdapter("country", "city", "streetAdress", "secondaryAdress")
fun bindAddress(
    textView: TextView,
    country: String?,
    city: String?,
    streetAdress: String?,
    secondaryAdress: String?
) {
    ifLet(country, city, streetAdress, secondaryAdress) {
        textView.text = it.filter { it.isNotEmpty() }.joinToString(separator = ", ")
    }
}

@BindingAdapter("from", "to")
fun bindDate(textView: TextView, from: String?, to: String?) {
    ifLet(from, to) { (from, to) ->
        textView.text = textView.context.getString(R.string.date, from, to)
    }
}

@BindingAdapter("text", "error")
fun bindTextInputLayout(textInputLayout: TextInputLayout, text: String?, error: String) {
    textInputLayout.error = if (text.isNullOrEmpty()) error else ""
}

@BindingAdapter("textWatcher")
fun bindEditTextTextWatcher(editText: EditText, textWatcher: TextWatcher) {
    with(editText) {
        addTextChangedListener(textWatcher)
    }
}

@BindingAdapter("isClickable")
fun bindButtonIsClickable(button: Button, isClickable: Boolean) {
    with(button) {
        this.isClickable = isClickable
        alpha = if (isClickable) 1f else 0.5f
    }
}

inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}