package com.wordpress.lonelytripblog

import androidx.fragment.app.FragmentManager

interface NavigationController {
    fun goToDefaultFragment()
    fun goToListFragment()
}

class NavigationControllerImpl(private val manager: FragmentManager) : NavigationController {
    override fun goToDefaultFragment() {
        manager.beginTransaction().replace(R.id.fragment_container, DefaultFragment()).commit()
    }

    override fun goToListFragment() {
        manager.beginTransaction().replace(R.id.fragment_container, ListFragment()).commit()
    }
}