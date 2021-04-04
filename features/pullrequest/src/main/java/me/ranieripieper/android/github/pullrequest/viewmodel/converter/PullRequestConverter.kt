package me.ranieripieper.android.github.pullrequest.viewmodel.converter

import me.ranieripieper.android.github.core.viewmodel.ResourceManager
import me.ranieripieper.android.github.pullrequest.data.model.PullRequest
import me.ranieripieper.android.github.pullrequest.data.model.PullRequestStateEnum
import me.ranieripieper.android.github.pullrequest.viewmodel.PullRequestItem
import me.ranieripieper.android.github.pullrequest.viewmodel.PullRequestPresentation
import me.ranieripieper.android.github.pullrequest.viewmodel.ViewState
import me.ranieripieper.android.github.ui.R
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class PullRequestConverter @Inject internal constructor(private val resourceManager: ResourceManager) {

    fun convertPullRequests(
        response: List<PullRequest>
    ): ViewState {

        if (response.isEmpty()) {
            return getViewStateEmpty()
        }

        val items = mutableListOf<PullRequestItem>()
        var open = 0
        var closed = 0
        response.forEach {
            items.add(convertPullRequest(it))
            when (it.state) {
                PullRequestStateEnum.OPEN -> open++
                PullRequestStateEnum.CLOSED -> closed++
            }
        }

        return ViewState.Content(
            PullRequestPresentation(
                openText = resourceManager.getString(
                    me.ranieripieper.android.github.pullrequest.R.string.pull_request_open,
                    open
                ),
                closedText = resourceManager.getString(
                    me.ranieripieper.android.github.pullrequest.R.string.pull_request_closed,
                    closed
                ),
                items = items
            )
        )
    }

    private fun convertPullRequest(pullRequest: PullRequest): PullRequestItem {
        return PullRequestItem(
            name = pullRequest.title,
            description = pullRequest.body,
            url = pullRequest.htmlUrl,
            username = pullRequest.user.login,
            avatarUrl = pullRequest.user.avatarUrl
        )
    }

    fun getViewStateError(error: Throwable): ViewState {
        if (error is ConnectException || error is UnknownHostException) {
            return ViewState.Error(resourceManager.getString(R.string.connection_error_message))
        }
        return ViewState.Error(resourceManager.getString(R.string.default_error_message))
    }

    private fun getViewStateEmpty(): ViewState {
        return ViewState.Error(resourceManager.getString(R.string.default_error_empty_items))
    }
}
