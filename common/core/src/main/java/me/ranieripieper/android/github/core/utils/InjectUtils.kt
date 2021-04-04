package me.ranieripieper.android.github.core.utils

import android.content.Context
import me.ranieripieper.android.github.core.di.CoreComponentProvider
import me.ranieripieper.android.github.core.di.component.CoreComponent

object InjectUtils {

    fun provideBaseComponent(applicationContext: Context): CoreComponent {
        return if (applicationContext is CoreComponentProvider) {
            (applicationContext as CoreComponentProvider).provideCoreComponent()
        } else {
            throw IllegalStateException("Provide the application context which implement BaseComponentProvider")
        }
    }

}