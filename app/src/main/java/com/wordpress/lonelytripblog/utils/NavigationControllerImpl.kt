package com.wordpress.lonelytripblog.utils

import androidx.fragment.app.FragmentManager
import com.wordpress.lonelytripblog.R
import com.wordpress.lonelytripblog.ui.DefaultFragment
import com.wordpress.lonelytripblog.ui.ListFragment

class NavigationControllerImpl(private val manager: FragmentManager) : NavigationController {
    override fun goToDefaultFragment() {
        manager.beginTransaction().replace(R.id.fragment_container, DefaultFragment()).commit()
    }

    override fun goToListFragment() {
        manager.beginTransaction().replace(R.id.fragment_container, ListFragment()).commit()
    }
}