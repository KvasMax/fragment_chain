package com.erros.minimax.ciceronetoothpick

import android.app.Application
import com.erros.minimax.ciceronetoothpick.di.Scopes
import com.erros.minimax.ciceronetoothpick.di.module.AppModule
import timber.log.Timber
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

/**
 * Created by minimax on 1/28/18.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initToothpick()
        openAppScope()
        initLogger()
    }

    private fun initToothpick() {
        val configuration = if (BuildConfig.DEBUG) Configuration.forDevelopment() else Configuration.forProduction()

        configuration.disableReflection()
        Toothpick.setConfiguration(configuration)

        FactoryRegistryLocator.setRootRegistry(com.erros.minimax.ciceronetoothpick.FactoryRegistry())
        MemberInjectorRegistryLocator.setRootRegistry(com.erros.minimax.ciceronetoothpick.MemberInjectorRegistry())
    }

    private fun openAppScope() {
        val appScope = Toothpick.openScope(Scopes.APP)
        appScope.installModules(AppModule(this))
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}