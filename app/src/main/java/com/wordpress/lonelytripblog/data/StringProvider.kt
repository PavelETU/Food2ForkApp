package com.wordpress.lonelytripblog.data

import android.content.res.Resources

class StringProvider(private val resources: Resources) {
    public fun getStringById(id: Int): String = resources.getString(id)
}