package me.ranieripieper.android.github.core.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.ranieripieper.android.github.core.viewmodel.ResourceManager

@Module
class CoreModule {
    @Provides
    fun provideResourceManager(context: Context) = ResourceManager(context)
}