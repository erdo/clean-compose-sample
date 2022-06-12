package foo.bar.clean

import androidx.multidex.MultiDexApplication
import co.early.fore.kt.core.delegate.DebugDelegateDefault
import co.early.fore.kt.core.delegate.Fore
import foo.bar.clean.di.dataModule
import foo.bar.clean.di.domainModule
import foo.bar.clean.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Copyright © 2019 early.co. All rights reserved.
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        inst = this

        if (BuildConfig.DEBUG) {
            Fore.setDelegate(DebugDelegateDefault(tagPrefix = "clean_"))
        }

        startKoin {
            if (BuildConfig.DEBUG) {
                // Use Koin Android Logger
                androidLogger(Level.ERROR) // TODO Koin 3.2.0 will bring Kotlin 1.6 then we can remove the Level.Error limit https://github.com/InsertKoinIO/koin/issues/1188
            }
            // declare Android context
            androidContext(this@App)
            // declare modules to use
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    uiModule
                )
            )
            allowOverride(true)
        }

        init()
    }

    companion object {
        lateinit var inst: App private set

        fun init() {
            // run any initialisation code here

//            val persista: PerSista = inst.get()
//            persista.wipeEverything {}
        }
    }
}
