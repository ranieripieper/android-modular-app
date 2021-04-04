package me.ranieripieper.android.github.pullrequest.data.model

import com.google.gson.annotations.SerializedName

data class User(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
)