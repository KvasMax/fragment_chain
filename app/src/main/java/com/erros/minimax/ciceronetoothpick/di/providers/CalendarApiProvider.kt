package com.erros.minimax.ciceronetoothpick.di.providers

import com.erros.minimax.ciceronetoothpick.data.network.LiturgicalCalendarAPI
import com.erros.minimax.ciceronetoothpick.di.qualifier.CalendarServerPath
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by milkman on 31.01.18.
 */
class CalendarApiProvider
@Inject constructor(private val okHttpClient: OkHttpClient,
                    @CalendarServerPath private val serverPath: String)
    : Provider<LiturgicalCalendarAPI> {

    override fun get(): LiturgicalCalendarAPI =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(serverPath)
                    .build()
                    .create(LiturgicalCalendarAPI::class.java)

}