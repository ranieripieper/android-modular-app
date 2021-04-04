package me.ranieripieper.android.github.gitrepository.data.model

import com.google.gson.annotations.SerializedName

data class GitRepository(
    val id: Long,
    val name: String,
    val owner: Owner,
    val description: String,
    @SerializedName("forks_count")
    val forksCount: Long,
    @SerializedName("stargazers_count")
    val stargazersCount: Long
)