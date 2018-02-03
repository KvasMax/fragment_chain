package com.erros.minimax.ciceronetoothpick.presentation.board.gallery

import com.erros.minimax.ciceronetoothpick.data.repository.PictureRepository
import com.erros.minimax.ciceronetoothpick.domain.model.Picture
import com.erros.minimax.ciceronetoothpick.presentation.base.AbstractBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by milkman on 31.01.18.
 */
class BoardPresenter
@Inject constructor(private val router: Router,
                    private val pictureRepository: PictureRepository)
    : AbstractBasePresenter<BoardContract.View>(), BoardContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val subject: PublishSubject<Picture> = PublishSubject.create()
    private val buffer = ArrayList<Picture>()
    private var disposable: Disposable? = null

    override fun onCreate() {
        super.onCreate()
        subscriptions.add(
                pictureRepository.getImages()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            subject.onNext(it)
                        }, {
                            handleError(it)
                        })
        )
    }

    override fun onViewAttached(view: BoardContract.View) {
        super.onViewAttached(view)
        if (buffer.isNotEmpty()) {
            for (picture in buffer) {
                view.addImageToList(picture)
            }
            buffer.clear()
        }
        subscribeOnUpdate()
    }

    private fun subscribeOnUpdate() {
        disposable = subject.subscribe({
            view?.addImageToList(it) ?: buffer.add(it)
        }, {
            handleError(it)
        })
    }

    override fun onBackPressed() {
        router.exit()
    }

    override fun onViewDetached() {
        super.onViewDetached()
        disposable?.dispose()
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.clear()
    }
}