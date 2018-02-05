package com.erros.minimax.ciceronetoothpick.presentation.board.picture

import com.erros.minimax.ciceronetoothpick.di.qualifier.PictureUrl
import com.erros.minimax.ciceronetoothpick.presentation.base.AbstractBasePresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 05.02.18.
 */
class PicturePresenter
@Inject constructor(private val router: Router,
                    @PictureUrl private val pictureUrl: String)
    : AbstractBasePresenter<PictureContract.View>(), PictureContract.Presenter {

    override fun onViewAttached(view: PictureContract.View) {
        super.onViewAttached(view)
        view.showPicture(pictureUrl)
    }

    override fun onBackPressed() {
        router.exit()
    }
}