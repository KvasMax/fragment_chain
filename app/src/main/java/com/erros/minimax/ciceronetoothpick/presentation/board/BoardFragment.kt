package com.erros.minimax.ciceronetoothpick.presentation.board

import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment.*

/**
 * Created by minimax on 1/28/18.
 */
class BoardFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.fragment

    override fun initViews() {
        textView.text = "Board"
    }

    override fun inject() {

    }
}