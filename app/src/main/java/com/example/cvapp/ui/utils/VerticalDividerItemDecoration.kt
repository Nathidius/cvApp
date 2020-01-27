package com.example.cvapp.ui.utils

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration

class VerticalDividerItemDecoration(context: Context, @DrawableRes drawableRes: Int) :
    DividerItemDecoration(context, VERTICAL) {

    init {
        ContextCompat.getDrawable(context, drawableRes)?.let {
            setDrawable(it)
        }
    }
}