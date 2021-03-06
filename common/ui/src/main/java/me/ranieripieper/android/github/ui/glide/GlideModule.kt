package me.ranieripieper.android.github.ui.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.BuildConfig
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import me.ranieripieper.android.github.ui.R

@GlideModule
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_person_placeholder)
                .error(R.drawable.ic_person_placeholder)
        )
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(Log.DEBUG)
        }

        super.applyOptions(context, builder)
    }


}