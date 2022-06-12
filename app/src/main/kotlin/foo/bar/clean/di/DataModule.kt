package foo.bar.clean.di

import co.early.fore.core.time.SystemTimeWrapper
import co.early.fore.kt.core.delegate.Fore
import co.early.fore.kt.net.InterceptorLogging
import co.early.fore.kt.net.ktor.CallProcessorKtor
import co.early.fore.kt.net.ktor.CallWrapperKtor
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

val dataModule = module {

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
        CallWrapperKtor(
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
            wrapper = get(),
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
