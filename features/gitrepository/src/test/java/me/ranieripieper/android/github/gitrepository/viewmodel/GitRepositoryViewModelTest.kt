package me.ranieripieper.android.github.gitrepository.viewmodel

import io.reactivex.Single
import me.ranieripieper.android.github.gitrepository.BaseUnitTest
import me.ranieripieper.android.github.gitrepository.data.model.GitRepositoryResponse
import me.ranieripieper.android.github.gitrepository.data.repository.GitRepositoryRepository
import me.ranieripieper.android.github.gitrepository.testObserver
import me.ranieripieper.android.github.gitrepository.viewmodel.converter.GitRespositoryConverter
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GitRepositoryViewModelTest : BaseUnitTest() {

    private val query = "language:java"

    @Mock
    private lateinit var repository: GitRepositoryRepository

    @Mock
    private lateinit var converter: GitRespositoryConverter

    private lateinit var viewModel: GitRepositoryViewModel

    @Test
    fun `searchRepositories success`() {
        val response = Mockito.mock(GitRepositoryResponse::class.java)
        Mockito.`when`(repository.searchRepositories(query, 1)).thenReturn(Single.just(response))
        Mockito.`when`(converter.convertGitRepositories(response)).thenReturn(
            ViewState.Content(
                emptyList()
            )
        )
        viewModel = GitRepositoryViewModel(repository, converter)

        val viewStates = viewModel.viewState.testObserver().observedValues
        Assert.assertEquals(1, viewStates.size)
        Assert.assertTrue(viewStates[0] is ViewState.Content)
    }

    @Test
    fun `searchRepositories success empty`() {
        val response = Mockito.mock(GitRepositoryResponse::class.java)
        Mockito.`when`(repository.searchRepositories(query, 1)).thenReturn(Single.just(response))
        Mockito.`when`(converter.convertGitRepositories(response)).thenReturn(
            ViewState.Empty("empty message")
        )
        viewModel = GitRepositoryViewModel(repository, converter)

        val viewStates = viewModel.viewState.testObserver().observedValues
        Assert.assertEquals(1, viewStates.size)
        Assert.assertTrue(viewStates[0] is ViewState.Empty)
    }

    @Test
    fun `searchRepositories error`() {
        val error = RuntimeException()
        Mockito.`when`(repository.searchRepositories(query, 1))
            .thenReturn(Single.error(error))
        Mockito.`when`(converter.getViewStateError(error)).thenReturn(
            ViewState.Error("error message")
        )

        viewModel = GitRepositoryViewModel(repository, converter)

        val viewStates = viewModel.viewState.testObserver().observedValues
        Assert.assertEquals(1, viewStates.size)
        Assert.assertTrue(viewStates[0] is ViewState.Error)
    }
}