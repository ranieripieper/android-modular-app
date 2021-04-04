package me.ranieripieper.android.github.gitrepository.viewmodel

data class GitRepositoryPresentation(
    val name: String,
    val description: String,
    val forks: String,
    val stars: String,
    val username: String,
    val avatarUrl: String
)