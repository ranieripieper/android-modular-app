package me.ranieripieper.android.github.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import me.ranieripieper.android.github.di.scopes.MainActivityScope

@Module
abstract class ApplicationModule {

    @Binds
    @MainActivityScope
    internal abstract fun provideContext(application: Application): Context
}