package com.erros.minimax.ciceronetoothpick.presentation.model

/**
 * Created by milkman on 06.02.18.
 */
sealed class ListItem {
    class ProgressItem() : ListItem()
    class EmptyItem() : ListItem()
    class ErrorItem() : ListItem()
}