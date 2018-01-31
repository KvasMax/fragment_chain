package com.erros.minimax.ciceronetoothpick.di.providers

import com.erros.minimax.ciceronetoothpick.di.qualifier.PicServerPath
import com.erros.minimax.ciceronetoothpick.model.network.PicsumApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by milkman on 31.01.18.
 */
class PicsumApiProvider
@Inject constructor(private val okHttpClient: OkHttpClient,
                    @PicServerPath private val serverPath: String)
    : Provider<PicsumApi> {

    override fun get(): PicsumApi =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(serverPath)
                    .build()
                    .create(PicsumApi::class.java)

}