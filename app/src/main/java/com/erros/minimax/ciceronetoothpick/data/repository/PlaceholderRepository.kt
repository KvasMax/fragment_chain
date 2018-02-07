package com.erros.minimax.ciceronetoothpick.data.repository

import com.erros.minimax.ciceronetoothpick.data.network.PlaceholderApi
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import javax.inject.Inject


/**
 * Created by milkman on 06.02.18.
 */
class PlaceholderRepository
@Inject constructor(private val placeholderApi: PlaceholderApi) {

    fun getUsers() = async(CommonPool) { placeholderApi.getUsers().execute().body() ?: listOf() }

}