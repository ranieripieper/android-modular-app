package me.ranieripieper.android.github.pullrequest.data.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
    val id: Long,
    val title: String,
    @SerializedName("state")
    val state: PullRequestStateEnum,
    val body: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val user: User
)