package me.ranieripieper.android.github.pullrequest.data.repository

import io.reactivex.Single
import me.ranieripieper.android.github.pullrequest.data.model.PullRequest
import me.ranieripieper.android.github.pullrequest.data.remote.PullRequestApi
import javax.inject.Inject

class PullRequestRepository @Inject constructor(private val api: PullRequestApi) {

    fun getPullRequests(
        owner: String,
        repository: String
    ): Single<List<PullRequest>> {
        return api.getPullRequests(owner = owner, repository = repository)
    }
}