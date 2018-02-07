package com.erros.minimax.ciceronetoothpick.di.providers

import android.content.Context
import com.erros.minimax.ciceronetoothpick.R
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by milkman on 31.01.18.
 */
class PlaceholderPathProvider
@Inject constructor(context: Context)
    : Provider<String> {

    private val path = context.getString(R.string.placeholder_url)

    override fun get(): String = path
}