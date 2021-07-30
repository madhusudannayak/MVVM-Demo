package com.example.mvvmusingjetpack.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.mvvmusingjetpack.R
import com.example.mvvmusingjetpack.model.ColorItem

class ColorSpinnerAdapter(context: Context, colorList: List<ColorItem>) : ArrayAdapter<ColorItem>(context, 0, colorList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {

        val colorPosition = getItem(position)

        val view = convertView ?: LayoutInflater.from(context).inflate(
                R.layout.spinner_item,
                parent,
                false
        )
        view.findViewById<ImageView>(R.id.colorImage).setImageResource(colorPosition!!.image)


        return view
    }
}