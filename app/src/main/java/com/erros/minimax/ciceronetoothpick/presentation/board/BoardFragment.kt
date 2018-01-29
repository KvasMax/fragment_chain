package com.erros.minimax.ciceronetoothpick.presentation.board

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.presentation.Screens
import kotlinx.android.synthetic.main.fragment.*
import toothpick.Toothpick

/**
 * Created by minimax on 1/28/18.
 */
class BoardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment, container, false)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        textView.text = "board"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scope = Toothpick.openScope(Scopes.MAIN_ACTIVITY_SCOPE)
        Toothpick.inject(this, scope)
    }
}