package com.erros.minimax.ciceronetoothpick.model.network

import com.erros.minimax.ciceronetoothpick.model.dto.Image
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by milkman on 31.01.18.
 */
interface PicsumApi {

    @GET("/list")
    fun getImageList(): Single<List<Image>>

}