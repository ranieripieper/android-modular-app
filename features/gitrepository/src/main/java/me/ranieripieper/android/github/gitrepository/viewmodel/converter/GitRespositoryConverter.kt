package me.ranieripieper.android.github.gitrepository.viewmodel.converter

import me.ranieripieper.android.github.core.viewmodel.ResourceManager
import me.ranieripieper.android.github.gitrepository.data.model.GitRepository
import me.ranieripieper.android.github.gitrepository.data.model.GitRepositoryResponse
import me.ranieripieper.android.github.gitrepository.viewmodel.GitRepositoryPresentation
import me.ranieripieper.android.github.gitrepository.viewmodel.ViewState
import me.ranieripieper.android.github.ui.R
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class GitRespositoryConverter @Inject internal constructor(private val resourceManager: ResourceManager) {

    fun getViewStateError(error: Throwable): ViewState {
        if (error is ConnectException || error is UnknownHostException) {
            return ViewState.Error(resourceManager.getString(R.string.connection_error_message))
        }
        return ViewState.Error(resourceManager.getString(R.string.default_error_message))
    }

    fun convertGitRepositories(
        response: GitRepositoryResponse
    ): ViewState {

        if (response.items.isEmpty()) {
            return getViewStateEmpty()
        }

        val result = mutableListOf<GitRepositoryPresentation>()
        response.items.forEach {
            result.add(convertGitRepository(it))
        }
        return ViewState.Content(result)
    }

    private fun convertGitRepository(gitRepository: GitRepository): GitRepositoryPresentation {
        return GitRepositoryPresentation(
            name = gitRepository.name,
            description = gitRepository.description,
            forks = formatNumber(gitRepository.forksCount),
            stars = formatNumber(gitRepository.stargazersCount),
            username = gitRepository.owner.login,
            avatarUrl = gitRepository.owner.avatarUrl
        )
    }

    private fun formatNumber(number: Long): String {
        return "%,d".format(number).replace(",", ".")
    }

    private fun getViewStateEmpty(): ViewState {
        return ViewState.Empty(resourceManager.getString(R.string.default_error_empty_items))
    }
}
