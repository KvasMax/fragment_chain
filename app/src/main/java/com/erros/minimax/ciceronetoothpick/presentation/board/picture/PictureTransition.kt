package com.erros.minimax.ciceronetoothpick.presentation.board.picture

import android.annotation.TargetApi
import android.os.Build
import android.support.transition.ChangeBounds
import android.support.transition.ChangeImageTransform
import android.support.transition.ChangeTransform
import android.support.transition.TransitionSet

/**
 * Created by milkman on 05.02.18.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class PictureTransition() : TransitionSet() {


    init {
        ordering = ORDERING_TOGETHER
        addTransition(ChangeBounds())
        addTransition(ChangeTransform())
        addTransition(ChangeImageTransform())
    }

}