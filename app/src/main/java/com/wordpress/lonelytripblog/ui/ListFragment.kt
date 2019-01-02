package com.wordpress.lonelytripblog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wordpress.lonelytripblog.R
import com.wordpress.lonelytripblog.viewmodel.Food2ForkViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    val viewModel: Food2ForkViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getListOfRecipes().observe(this, Observer { if (it != null) {
            progress_bar.visibility = View.GONE
            list_of_items.visibility = View.VISIBLE
            list_of_items.layoutManager = LinearLayoutManager(context)
            list_of_items.adapter = RecipeListAdapter(it)
        }
        })
        viewModel.getMessageToDisplay().observe(this, Observer { if (it != null) {
            progress_bar.visibility = View.GONE
            message.visibility = View.VISIBLE
            message.text = it
        }
        })
    }

}
