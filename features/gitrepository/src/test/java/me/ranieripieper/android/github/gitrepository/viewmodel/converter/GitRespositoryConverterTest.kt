package me.ranieripieper.android.github.gitrepository.viewmodel.converter

import me.ranieripieper.android.github.core.viewmodel.ResourceManager
import me.ranieripieper.android.github.gitrepository.data.model.GitRepository
import me.ranieripieper.android.github.gitrepository.data.model.GitRepositoryResponse
import me.ranieripieper.android.github.gitrepository.data.model.Owner
import me.ranieripieper.android.github.gitrepository.viewmodel.ViewState
import me.ranieripieper.android.github.ui.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.net.ConnectException
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class GitRespositoryConverterTest {

    @Mock
    private lateinit var resourceManager: ResourceManager

    private lateinit var conveter: GitRespositoryConverter

    @Before
    fun setup() {
        conveter = GitRespositoryConverter(resourceManager)
    }

    @Test
    fun `convertGitRepositories empty`() {
        val message = "empty result"
        Mockito.`when`(resourceManager.getString(R.string.default_error_empty_items))
            .thenReturn(message)
        val viewState = conveter.convertGitRepositories(GitRepositoryResponse(items = emptyList()))

        Assert.assertTrue(viewState is ViewState.Empty)
        Assert.assertEquals(message, (viewState as ViewState.Empty).message)
    }

    @Test
    fun `convertGitRepositories`() {
        val id = 1000L
        val name = "name $id"
        val description = "description $id"
        val avatarUrl = "url $id"
        val login = "login $id"
        val gitRepository = GitRepository(
            id = id,
            name = name,
            owner = Owner(
                id = id,
                login = login,
                avatarUrl = avatarUrl
            ),
            description = description,
            forksCount = id,
            stargazersCount = id + 1
        )
        val viewState =
            conveter.convertGitRepositories(GitRepositoryResponse(items = listOf(gitRepository)))

        Assert.assertTrue(viewState is ViewState.Content)
        val viewStateResult = (viewState as ViewState.Content)
        val gitRepositoryPresentation = viewStateResult.items[0]
        Assert.assertEquals(1, viewStateResult.items.size)
        Assert.assertEquals(name, gitRepositoryPresentation.name)
        Assert.assertEquals(description, gitRepositoryPresentation.description)
        Assert.assertEquals(avatarUrl, gitRepositoryPresentation.avatarUrl)
        Assert.assertEquals(login, gitRepositoryPresentation.username)
        Assert.assertEquals("1.000", gitRepositoryPresentation.forks)
        Assert.assertEquals("1.001", gitRepositoryPresentation.stars)
    }

    @Test
    fun `getViewStateError ConnectException`() {
        val error = "ConnectException"
        Mockito.`when`(resourceManager.getString(R.string.connection_error_message))
            .thenReturn(error)
        val viewState = conveter.getViewStateError(ConnectException())

        Assert.assertTrue(viewState is ViewState.Error)
        Assert.assertEquals(error, (viewState as ViewState.Error).error)
    }

    @Test
    fun `getViewStateError UnknownHostException`() {
        val error = "UnknownHostException"
        Mockito.`when`(resourceManager.getString(R.string.connection_error_message))
            .thenReturn(error)
        val viewState = conveter.getViewStateError(UnknownHostException())

        Assert.assertTrue(viewState is ViewState.Error)
        Assert.assertEquals(error, (viewState as ViewState.Error).error)
    }

    @Test
    fun `getViewStateError DefaultError`() {
        val error = "defaultError"
        Mockito.`when`(resourceManager.getString(R.string.default_error_message))
            .thenReturn(error)
        val viewState = conveter.getViewStateError(Exception())

        Assert.assertTrue(viewState is ViewState.Error)
        Assert.assertEquals(error, (viewState as ViewState.Error).error)
    }

}