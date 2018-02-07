package com.erros.minimax.ciceronetoothpick.data.network

import com.erros.minimax.ciceronetoothpick.data.model.Person
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by milkman on 07.02.18.
 */
interface PlaceholderApi {

    @GET("/users")
    fun getUsers(): Call<List<Person>>

}

