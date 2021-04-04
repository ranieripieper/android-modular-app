package me.ranieripieper.android.github.pullrequest.data.remote

import io.reactivex.Single
import me.ranieripieper.android.github.pullrequest.data.model.PullRequest
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestApi {

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repository") repository: String
    ): Single<List<PullRequest>>
}