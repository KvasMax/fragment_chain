package com.erros.minimax.ciceronetoothpick.presentation.settings

import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment.*

/**
 * Created by minimax on 1/28/18.
 */
class SettingsFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.fragment

    override fun initViews() {
        textView.text = "settings"
    }

    override fun inject() {
    }
}