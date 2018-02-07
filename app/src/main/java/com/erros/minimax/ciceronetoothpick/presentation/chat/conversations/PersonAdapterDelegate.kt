package com.erros.minimax.ciceronetoothpick.presentation.chat.conversations

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.erros.minimax.ciceronetoothpick.R
import com.erros.minimax.ciceronetoothpick.data.model.Person
import com.erros.minimax.ciceronetoothpick.extension.inflate
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import kotlinx.android.synthetic.main.item_person.view.*

/**
 * Created by milkman on 07.02.18.
 */
class PersonAdapterDelegate : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = PersonViewHolder(parent)

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean = items[position] is Person

    override fun onBindViewHolder(items: MutableList<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        (holder as PersonViewHolder).bind((items[position] as Person))
    }

    private class PersonViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_person)) {

        fun bind(person: Person) {
            itemView.nameTextView.text = "${person.username} - ${person.name}"
            itemView.phoneTextView.text = person.phone
        }

    }
}