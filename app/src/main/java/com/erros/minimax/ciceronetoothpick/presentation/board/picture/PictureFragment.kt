package com.erros.minimax.ciceronetoothpick.presentation.board.picture

import android.os.Build
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.PictureModule
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenterFragment
import kotlinx.android.synthetic.main.fragment_picture.*
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by milkman on 05.02.18.
 */
class PictureFragment : BasePresenterFragment<PictureContract.Presenter, PictureContract.View>(), PictureContract.View {

    companion object {
        private const val ARG_URL = "ARG_URL"
        const val TRANSITION_NAME = "PICTURE"

        fun getInstance(url: String): PictureFragment {
            val fragment = PictureFragment()
            val bundle = Bundle()
            bundle.putString(ARG_URL, url)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var presenter: PictureContract.Presenter

    override val layout: Int
        get() = R.layout.fragment_picture

    override fun initViews() {
        imageView.setOnClickListener { presenter.onImageClick() }
    }

    override fun inject() {
        Toothpick.openScopes(Scopes.BOARD, Scopes.PICTURE).apply {
            arguments?.let {
                installModules(PictureModule(it.getString(ARG_URL)))
            }
            Toothpick.inject(this@PictureFragment, this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.transitionName = TRANSITION_NAME
        }
    }

    override fun showPicture(url: String) {
        Glide.with(this).load(url).into(imageView)
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(Scopes.PICTURE)
    }

    override val basePresenter: PictureContract.Presenter
        get() = presenter
}