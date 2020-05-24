package xyz.wayhua.kivyv103.di

import com.android.kudago_kotlin.model.data.storage.Prefs
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.wayhua.kivyv103.data.repository.EventRepository
import xyz.wayhua.kivyv103.ui.main.fragment.MainFragment

import xyz.wayhua.kivyv103.ui.main.fragment.MainViewModel

val prefsModule = module {
    single {
        Prefs(get())
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val fragmentModule = module {
    factory { MainFragment() }
}
val repoModule = module {

    single {
        EventRepository(get(), get())
    }
}
val allModules =
    listOf(prefsModule, viewModelModule, repoModule, fragmentModule, remoteDatasourceModule)