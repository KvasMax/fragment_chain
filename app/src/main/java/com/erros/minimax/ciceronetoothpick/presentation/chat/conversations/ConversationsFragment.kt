package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import android.support.v7.widget.LinearLayoutManager
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.data.model.Person
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.extension.visible
import com.erros.minimax.ciceronetoothpick.presentation.base.BasePresenterFragment
import kotlinx.android.synthetic.main.fragment_conversations.*
import toothpick.Toothpick
import javax.inject.Inject

/**
 * Created by minimax on 1/28/18.
 */
class ConversationsFragment : BasePresenterFragment<ConversationsContract.Presenter, ConversationsContract.View>(), ConversationsContract.View {

    @Inject
    lateinit var presenter: ConversationsContract.Presenter

    private val adapter = ConversationsAdapter()

    override val layout: Int
        get() = R.layout.fragment_conversations

    override fun initViews() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        swipeRefreshLayout.setOnRefreshListener { presenter.onRefresh() }
    }

    override fun showProgress(show: Boolean) {
        progressBar.visible(show)
        recyclerView.visible(!show)
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showUsers(persons: List<Person>) {
        adapter.setData(persons)
    }

    override fun inject() {
        Toothpick.inject(this, Toothpick.openScope(Scopes.CHAT))
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    override val basePresenter: ConversationsContract.Presenter
        get() = presenter
}