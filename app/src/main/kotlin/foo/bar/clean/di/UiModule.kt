package foo.bar.clean.di

import foo.bar.clean.App
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val uiModule = module {

    /**
     * Misc
     */

    single { androidContext() as App }

    /**
     * Compose ViewModels
     */

//    viewModel {
//        DashboardViewModel(
//            weatherModel = get(),
//            refreshModel = get()
//        )
//    }
}
