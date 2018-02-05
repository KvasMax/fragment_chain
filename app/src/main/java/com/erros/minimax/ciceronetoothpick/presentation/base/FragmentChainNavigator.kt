package com.erros.minimax.ciceronetoothpick.presentation.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.*
import java.util.*

/**
 * Created by milkman on 02.02.18.
 */
abstract class FragmentChainNavigator
constructor(private val containerId: Int,
            private val fragmentManager: FragmentManager,
            private val activity: FragmentActivity?)
    : Navigator {

    public var preventFragmentDestruction = true

    private var localStackCopy = LinkedList<String>()

    override fun applyCommands(commands: Array<out Command>) {
        fragmentManager.executePendingTransactions()
        copyStackToLocal()
        for (command in commands) {
            applyCommand(command)
        }
    }

    private fun copyStackToLocal() {
        localStackCopy = LinkedList<String>()
        for (i in 0 until fragmentManager.backStackEntryCount) {
            localStackCopy.add(fragmentManager.getBackStackEntryAt(i).name)
        }
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> forward(command)
            is Back -> back()
            is Replace -> replace(command)
            is BackTo -> backTo(command)
            is SystemMessage -> showSystemMessage(command.message)
        }
    }

    protected open fun forward(command: Forward) {
        val newFragment = createFragment(command.screenKey, command.transitionData)

        if (newFragment == null) {
            unknownScreen(command)
            return
        }


        val transaction = fragmentManager.beginTransaction()

        val currentFragment = if (fragmentManager.fragments.isEmpty()) null else fragmentManager.fragments.last()

        currentFragment?.let {
            if (preventFragmentDestruction) {
                transaction.hide(it)
            } else {
                transaction.detach(it)
            }
        }

        setupFragmentTransactionAnimation(
                command,
                currentFragment,
                newFragment,
                transaction
        )

        transaction
                .add(containerId, newFragment, command.screenKey)
                .addToBackStack(command.screenKey)
                .commit()

        localStackCopy.add(command.screenKey)
    }

    protected open fun back() {
        if (localStackCopy.size > 0) {
            fragmentManager.apply {
                popBackStackImmediate()
                localStackCopy.pop()
            }
        } else {
            exit()
        }
    }

    protected open fun replace(command: Replace) {
        val fragment = createFragment(command.screenKey, command.transitionData)

        if (fragment == null) {
            unknownScreen(command)
            return
        }

        if (localStackCopy.size > 0) {
            fragmentManager.popBackStackImmediate()
            localStackCopy.pop()

            val fragmentTransaction = fragmentManager.beginTransaction()

            val currentFragment = if (fragmentManager.fragments.isEmpty()) null else fragmentManager.fragments.last()

            currentFragment?.let {
                fragmentTransaction.hide(it)
            }

            setupFragmentTransactionAnimation(
                    command,
                    currentFragment,
                    fragment,
                    fragmentTransaction
            )

            fragmentTransaction
                    .add(containerId, fragment)
                    .addToBackStack(command.screenKey)
                    .commit()
            localStackCopy.add(command.screenKey)

        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransactionAnimation(
                    command,
                    fragmentManager.findFragmentById(containerId),
                    fragment,
                    fragmentTransaction
            )

            fragmentTransaction
                    .add(containerId, fragment)
                    .commit()
        }
    }

    protected open fun backTo(command: BackTo) {
        val key = command.screenKey

        if (key == null) {
            backToRoot()

        } else {
            val index = localStackCopy.indexOf(key)
            val size = localStackCopy.size

            if (index != -1) {
                for (i in 1 until size - index) {
                    localStackCopy.pop()
                }
                fragmentManager.popBackStack(key, 0)
            } else {
                backToUnexisting(command.screenKey)
            }
        }
    }

    protected open fun backToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        localStackCopy.clear()
    }

    protected open fun backToUnexisting(screenKey: String) {
        backToRoot()
    }

    protected open fun unknownScreen(command: Command) {
        throw RuntimeException("Can't create a screen for passed screenKey.")
    }

    protected open fun exit() {
        activity?.finish()
    }

    protected abstract fun createFragment(screenKey: String, data: Any?): Fragment?

    protected open fun showSystemMessage(message: String) {
    }

    protected open fun setupFragmentTransactionAnimation(command: Command,
                                                         currentFragment: Fragment?,
                                                         nextFragment: Fragment,
                                                         fragmentTransaction: FragmentTransaction) {
    }
}