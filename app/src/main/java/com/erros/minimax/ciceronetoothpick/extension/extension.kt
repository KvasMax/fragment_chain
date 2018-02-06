package com.erros.minimax.ciceronetoothpick.extension

import android.view.View

/**
 * Created by milkman on 06.02.18.
 */
fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
