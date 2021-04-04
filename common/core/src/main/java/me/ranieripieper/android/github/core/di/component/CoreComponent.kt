package me.ranieripieper.android.github.core.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.ranieripieper.android.github.core.di.module.CoreModule
import me.ranieripieper.android.github.core.di.module.NetworkModule
import me.ranieripieper.android.github.core.di.module.ViewModelModule
import me.ranieripieper.android.github.core.viewmodel.ResourceManager
import retrofit2.Retrofit
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        CoreModule::class,
    ]
)
interface CoreComponent {

    fun inject(app: Application)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun networkModule(appModule: NetworkModule): Builder

        @BindsInstance
        fun viewModelModule(viewModelModule: ViewModelModule): Builder

        @BindsInstance
        fun coreModule(coreModule: CoreModule): Builder

        fun build(): CoreComponent
    }

    fun getResourceManager(): ResourceManager

    fun getRetrofit(): Retrofit
}