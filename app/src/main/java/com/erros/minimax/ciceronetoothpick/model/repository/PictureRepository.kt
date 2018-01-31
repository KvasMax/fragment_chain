package com.erros.minimax.ciceronetoothpick.model.repository

import com.erros.minimax.ciceronetoothpick.Picture
import com.erros.minimax.ciceronetoothpick.model.network.PicsumApi
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by milkman on 31.01.18.
 */
class PictureRepository
@Inject constructor(private val picsumApi: PicsumApi) {

    fun getImages(): Observable<Picture> = Observable.zip(
            picsumApi.getImageList().flatMapObservable { Observable.fromIterable(it) },
            Observable.interval(500, TimeUnit.MILLISECONDS),
            BiFunction { item, time ->
                Picture("https://picsum.photos/600/200?image=${item.id}", item.id)
            }

    )


}