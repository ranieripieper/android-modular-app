package me.ranieripieper.android.github.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import me.ranieripieper.android.github.core.di.module.CoreModule
import me.ranieripieper.android.github.di.module.ApplicationModule
import me.ranieripieper.android.github.di.scopes.MainActivityScope

@MainActivityScope
@Component(
    modules = [
        ApplicationModule::class,
        CoreModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}