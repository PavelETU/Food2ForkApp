package com.wordpress.lonelytripblog.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.wordpress.lonelytripblog.R

class DefaultFragment : Fragment() {
    private var listener: GoClickEventListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_default, container, false)
        view.findViewById<Button>(R.id.go_button).setOnClickListener { onGoButtonPressed() }
        return view
    }

    private fun onGoButtonPressed() {
        listener?.onGoClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GoClickEventListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement GoClickEventListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface GoClickEventListener {
        fun onGoClicked()
    }
}
