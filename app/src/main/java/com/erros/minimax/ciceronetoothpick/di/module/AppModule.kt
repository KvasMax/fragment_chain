package com.erros.minimax.ciceronetoothpick.di.module

import android.content.Context
import com.erros.minimax.ciceronetoothpick.di.providers.OkHttpClientProvider
import okhttp3.OkHttpClient
import toothpick.config.Module

/**
 * Created by minimax on 1/28/18.
 */
class AppModule(context: Context): Module() {

    init {
        bind(Context::class.java).toInstance(context.applicationContext)
        bind(OkHttpClient::class.java).toProvider(OkHttpClientProvider::class.java).providesSingletonInScope()
    }


}