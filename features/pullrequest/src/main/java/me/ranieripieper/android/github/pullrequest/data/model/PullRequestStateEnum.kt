package me.ranieripieper.android.github.pullrequest.data.model

import com.google.gson.annotations.SerializedName

enum class PullRequestStateEnum {
    @SerializedName("open")
    OPEN,

    @SerializedName("closed")
    CLOSED
}