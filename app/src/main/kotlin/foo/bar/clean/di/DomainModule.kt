package foo.bar.clean.di

import foo.bar.clean.domain.tictactoe.Game
import foo.bar.clean.domain.wallet.Wallet
import org.koin.dsl.module

val domainModule = module {

    /**
     * Models and Mediators
     */

    single {
        Game(
            autoPlayerService = get(),
            perSista = get()
        )
    }

    single {
        Wallet(
            perSista = get(),
            logger = get()
        )
    }
}
