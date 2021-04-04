package me.ranieripieper.android.github.gitrepository.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
)