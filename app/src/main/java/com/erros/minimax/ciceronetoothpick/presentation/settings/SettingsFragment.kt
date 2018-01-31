package com.erros.minimax.ciceronetoothpick.presentation.settings

import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment.*
import timber.log.Timber
import toothpick.Toothpick

/**
 * Created by minimax on 1/28/18.
 */
class SettingsFragment : BaseFragment() {

    override val layout: Int
        get() = R.layout.fragment

    override fun initViews() {
        textView.text = "Settings"
        textView.setOnClickListener {
            Timber.d(Toothpick.openScope(Scopes.APP).toString())
        }
    }

    override fun inject() {

    }
}