package me.ranieripieper.android.github.pullrequest.viewmodel

data class PullRequestPresentation(
    val openedText: String,
    val closedText: String,
    val items: List<PullRequestItem>
)

data class PullRequestItem(
    val name: String,
    val description: String,
    val url: String,
    val username: String,
    val avatarUrl: String
)