package com.erros.minimax.ciceronetoothpick.presentation.board.gallery

import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.erros.minimax.ciceronetoothpick.Picture
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenterFragment
import kotlinx.android.synthetic.main.fragment_list.*
import net.idik.lib.slimadapter.SlimAdapter
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by minimax on 1/28/18.
 */
class BoardFragment : BasePresenterFragment<BoardContract.Presenter, BoardContract.View>(), BoardContract.View {

    @Inject
    lateinit var presenter: BoardContract.Presenter

    private val adapter by lazy {
        SlimAdapter.create()
                /*.enableDiff(object : SlimDiffUtil.Callback {
                    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = if (oldItem is Picture && newItem is Picture)
                        oldItem.id == newItem.id
                    else false

                    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
                })*/
                .register<Picture>(R.layout.item_gallery, { picture, injector ->
                    injector.with<ImageView>(R.id.imageView, {
                        activity?.apply {
                            Glide.with(this).load(picture.url).into(it)
                        }
                    })
                })
    }

    private val imageList = ArrayList<Picture>()

    override val layout: Int
        get() = R.layout.fragment_list

    override fun initViews() {
        adapter.attachTo(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun addImageToList(picture: Picture) {
        imageList.add(picture)
        adapter.updateData(imageList)
    }

    override fun inject() {
        Toothpick.inject(this, Toothpick.openScopes(Scopes.BOARD))
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override val basePresenter: BoardContract.Presenter
        get() = presenter
}