package com.erros.minimax.ciceronetoothpick.di.providers

import com.erros.minimax.ciceronetoothpick.data.network.PlaceholderApi
import com.erros.minimax.ciceronetoothpick.di.qualifier.PlaceholderPath
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by milkman on 31.01.18.
 */
class PlaceholderApiProvider
@Inject constructor(private val okHttpClient: OkHttpClient,
                    @PlaceholderPath private val serverPath: String)
    : Provider<PlaceholderApi> {

    override fun get(): PlaceholderApi =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(serverPath)
                    .build()
                    .create(PlaceholderApi::class.java)

}