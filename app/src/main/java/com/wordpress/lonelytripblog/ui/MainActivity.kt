package com.wordpress.lonelytripblog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wordpress.lonelytripblog.utils.NavigationController
import com.wordpress.lonelytripblog.utils.NavigationControllerImpl
import com.wordpress.lonelytripblog.R

class MainActivity : AppCompatActivity(), DefaultFragment.GoClickEventListener {

    private var navigationController: NavigationController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController = NavigationControllerImpl(supportFragmentManager)
        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null) {
            navigationController?.goToDefaultFragment()
        }
    }

    override fun onGoClicked() {
        navigationController?.goToListFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigationController = null
    }
}
