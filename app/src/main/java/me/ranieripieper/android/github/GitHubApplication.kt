package me.ranieripieper.android.github

import android.app.Application
import me.ranieripieper.android.github.core.di.CoreComponentProvider
import me.ranieripieper.android.github.core.di.component.CoreComponent
import me.ranieripieper.android.github.core.di.component.DaggerCoreComponent
import me.ranieripieper.android.github.core.di.module.CoreModule
import me.ranieripieper.android.github.core.di.module.NetworkModule
import me.ranieripieper.android.github.core.di.module.ViewModelModule

class GitHubApplication : Application(), CoreComponentProvider {

    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()

        coreComponent = DaggerCoreComponent
            .builder()
            .context(this)
            .coreModule(CoreModule())
            .networkModule(NetworkModule())
            .viewModelModule(ViewModelModule())
            .build()
        coreComponent.inject(this)
    }

    override fun provideCoreComponent(): CoreComponent {
        return coreComponent
    }

}