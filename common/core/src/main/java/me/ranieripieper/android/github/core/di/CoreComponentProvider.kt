package me.ranieripieper.android.github.core.di

import me.ranieripieper.android.github.core.di.component.CoreComponent

interface CoreComponentProvider {

    fun provideCoreComponent(): CoreComponent

}