package me.ranieripieper.android.github.gitrepository.data.remote

import io.reactivex.Single
import me.ranieripieper.android.github.core.utils.Constants
import me.ranieripieper.android.github.gitrepository.data.model.GitRepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepositoryApi {

    companion object {
        const val DEFAULT_SORT = "stars"
    }

    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int = Constants.API_FIRST_PAGE,
        @Query("per_page") perPage: Int = Constants.API_RESULTS_PER_PAGE,
        @Query("sort") sort: String = DEFAULT_SORT
    ): Single<GitRepositoryResponse>
}