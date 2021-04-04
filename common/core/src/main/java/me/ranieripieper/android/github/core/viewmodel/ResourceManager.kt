package me.ranieripieper.android.github.core.viewmodel

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceManager @Inject internal constructor(
    private val context: Context) {

    fun getString(@StringRes stringRes: Int): String {
        return context.getString(stringRes)
    }

    fun getString(@StringRes stringRes: Int, vararg formatArgs: Any): String {
        return context.getString(stringRes, *formatArgs)
    }

}
