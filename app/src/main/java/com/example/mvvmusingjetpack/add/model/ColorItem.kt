package com.example.mvvmusingjetpack.add.model

import com.example.mvvmusingjetpack.R

data class ColorItem(val image: Int)
    object Colors {

        private val images = intArrayOf(
                R.drawable.white,
                R.drawable.blue,
                R.drawable.pink,
                R.drawable.yellow,
                R.drawable.green,
                R.drawable.grey,
        )
        var list: ArrayList<ColorItem>? = null
            get() {

                if (field != null)
                    return field

                field = ArrayList()
                for (i in Colors.images.indices) {

                    val imageId = Colors.images[i]

                    val color = ColorItem(imageId)
                    field!!.add(color)
                }

                return field
            }
    }
