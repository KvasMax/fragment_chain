package com.erros.minimax.ciceronetoothpick.presentation.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.*
import java.util.*

/**
 * Created by milkman on 02.02.18.
 */
abstract class FragmentChainNavigator
constructor(private val containerId: Int, private val fragmentManager: FragmentManager)
    : Navigator {

    private var localStackCopy = LinkedList<String>()

    override fun applyCommands(commands: Array<out Command>) {
        fragmentManager.executePendingTransactions()

        copyStackToLocal()

        for (command in commands) {
            applyCommand(command)
        }
    }

    private fun copyStackToLocal() {
        localStackCopy = LinkedList<>()

        val stackSize = fragmentManager.backStackEntryCount
        for (i in 0 until stackSize) {
            localStackCopy.add(fragmentManager.getBackStackEntryAt(i).name)
        }
    }

    private fun applyCommand(command: Command) {
        if (command is Forward) {
            forward(command as Forward)
        } else if (command is Back) {
            back()
        } else if (command is Replace) {
            replace(command)
        } else if (command is BackTo) {
            backTo(command)
        } else if (command is SystemMessage) {
            showSystemMessage(command.message)
        }
    }

    private fun forward(command: Forward) {
        val newFragment = createFragment(command.screenKey, command.transitionData)

        if (newFragment == null) {
            unknownScreen(command)
            return
        }

        fragmentManager.apply {

            fragments.filterNot { it.isHidden }
                    .forEach {
                        beginTransaction()
                                .hide(it)
                                .commitNow()
                    }

            val transaction = beginTransaction()

            setupFragmentTransactionAnimation(
                    command,
                    findFragmentById(containerId),
                    newFragment,
                    transaction
            )

            transaction
                    .add(containerId, newFragment, command.screenKey)
                    .addToBackStack(command.screenKey)
                    .commit()
            localStackCopy.add(command.screenKey)
        }
    }

    override fun back() {
        if (localStackCopy.size > 0) {
            fragmentManager.apply {
                popBackStackImmediate()
                localStackCopy.pop()
                if (fragments.isNotEmpty()) {
                    beginTransaction()
                            .show(fragments.last())
                            .commitNow()
                }
            }
        } else {
            exit()
        }
    }

    private fun unknownScreen(command: Command) {
        throw RuntimeException("Can't create a screen for passed screenKey.")
    }

    protected abstract fun exit()

    protected abstract fun createFragment(screenKey: String, data: Any): Fragment?

    protected fun setupFragmentTransactionAnimation(command: Command,
                                                    currentFragment: Fragment,
                                                    nextFragment: Fragment,
                                                    fragmentTransaction: FragmentTransaction) {
    }
}