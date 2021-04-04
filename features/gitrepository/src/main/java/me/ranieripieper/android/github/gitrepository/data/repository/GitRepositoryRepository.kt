package me.ranieripieper.android.github.gitrepository.data.repository

import io.reactivex.Single
import me.ranieripieper.android.github.gitrepository.data.model.GitRepositoryResponse
import me.ranieripieper.android.github.gitrepository.data.remote.GitRepositoryApi
import javax.inject.Inject

class GitRepositoryRepository @Inject constructor(private val api: GitRepositoryApi) {

    fun searchRepositories(query: String, page: Int): Single<GitRepositoryResponse> {
        return api.searchRepositories(query = query, page = page)
    }
}