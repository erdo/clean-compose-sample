package foo.bar.clean.di

import co.early.fore.core.time.SystemTimeWrapper
import co.early.fore.kt.core.delegate.Fore
import co.early.fore.kt.net.InterceptorLogging
import co.early.fore.kt.net.ktor.CallProcessorKtor
import co.early.persista.PerSista
import foo.bar.clean.App
import foo.bar.clean.data.api.ktor.CustomKtorBuilder
import foo.bar.clean.data.api.ktor.ErrorHandler
import foo.bar.clean.data.api.ktor.GlobalRequestInterceptor
import foo.bar.clean.data.api.ktor.services.autoplayer.AutoPlayerApi
import foo.bar.clean.data.api.ktor.services.autoplayer.AutoPlayerServiceImp
import foo.bar.clean.data.api.ktor.services.autoplayer.smokemirrors.AutoPlayerInterceptor
import foo.bar.clean.domain.tictactoe.AutoPlayerService
import foo.bar.clean.domain.tictactoe.Game
import org.koin.dsl.module

val dataModule = module(override = true) {

    /**
     * Ktor
     */

    single {
        CustomKtorBuilder.create(
            GlobalRequestInterceptor(),
            AutoPlayerInterceptor { get() as Game },
            InterceptorLogging()
        )//logging interceptor should be the last one
    }

    single {
        CallProcessorKtor(
            ErrorHandler(get())
        )
    }

    /**
     * Persistence
     */

    single {
        PerSista(
            dataDirectory = (get() as App).filesDir,
            logger = get()
        )
    }

    /**
     * Network Services
     */

    single<AutoPlayerService> {
        AutoPlayerServiceImp(
            client = AutoPlayerApi.create(get()),
            processor = get(),
            logger = get()
        )
    }

    /**
     * Misc Data
     */

    single {
        SystemTimeWrapper()
    }

    single {
        Fore.getLogger()
    }
}
