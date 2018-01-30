package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenterFragment
import kotlinx.android.synthetic.main.fragment.*
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by minimax on 1/28/18.
 */
class ConversationsFragment : BasePresenterFragment(), ConversationsContract.View {

    @Inject
    lateinit var presenter: ConversationsContract.Presenter

    override val layout: Int
        get() = R.layout.fragment

    override fun initViews() {
        textView.text = "Conversations"
        textView.setOnClickListener {
            presenter.onOpenChatClick()
        }
    }

    override fun inject() {
        Toothpick.inject(this, Toothpick.openScope(Scopes.CHAT_SCOPE))
    }

    override fun attachPresenter() {
        presenter.attachView(this)
    }

    override fun detachPresenter() {
        presenter.detachView()
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }
}