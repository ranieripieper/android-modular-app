package me.ranieripieper.android.github.gitrepository.data.repository

import io.reactivex.Single
import me.ranieripieper.android.github.gitrepository.data.model.GitRepository
import me.ranieripieper.android.github.gitrepository.data.model.GitRepositoryResponse
import me.ranieripieper.android.github.gitrepository.data.model.Owner
import me.ranieripieper.android.github.gitrepository.data.remote.GitRepositoryApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GitRepositoryRepositoryTest {

    @Mock
    private lateinit var gitRepositoryApi: GitRepositoryApi

    private lateinit var gitRepositoryRepository: GitRepositoryRepository

    @Before
    fun setup() {
        gitRepositoryRepository = GitRepositoryRepository(gitRepositoryApi)
    }

    @Test
    fun `searchRepositories success`() {
        val id1 = 1L
        val id2 = 2L
        val query = "java"
        val page = 1

        val result =
            GitRepositoryResponse(listOf(createGitRepository(id1), createGitRepository(id2)))
        Mockito.`when`(gitRepositoryApi.searchRepositories(query, page))
            .thenReturn(Single.just(result))

        val testObserver = gitRepositoryRepository.searchRepositories(query, page).test()

        Assert.assertEquals(1, testObserver.valueCount())
        Assert.assertEquals(2, testObserver.values()[0].items.size)

        val item1 = testObserver.values()[0].items[0]
        val item2 = testObserver.values()[0].items[1]

        Assert.assertEquals(id1, item1.id)

        Assert.assertEquals("name $id1", item1.name)
        Assert.assertEquals("description $id1", item1.description)
        Assert.assertEquals(id1, item1.owner.id)
        Assert.assertEquals("login $id1", item1.owner.login)
        Assert.assertEquals("url $id1", item1.owner.avatarUrl)
        Assert.assertEquals(id1, item1.forksCount)
        Assert.assertEquals(id1 + 1, item1.stargazersCount)

        Assert.assertEquals("name $id2", item2.name)
        Assert.assertEquals("description $id2", item2.description)
        Assert.assertEquals(id2, item2.owner.id)
        Assert.assertEquals("login $id2", item2.owner.login)
        Assert.assertEquals("url $id2", item2.owner.avatarUrl)
        Assert.assertEquals(id2, item2.forksCount)
        Assert.assertEquals(id2 + 1, item2.stargazersCount)
    }

    @Test(expected = RuntimeException::class)
    fun `searchRepositories error`() {
        val query = "java"
        val page = 1
        Mockito.`when`(gitRepositoryApi.searchRepositories(query, page))
            .thenThrow(RuntimeException())

        gitRepositoryRepository.searchRepositories(query, page).test()
    }

    private fun createGitRepository(id: Long): GitRepository {
        return GitRepository(
            id = id,
            name = "name $id",
            owner = Owner(
                id = id,
                login = "login $id",
                avatarUrl = "url $id"
            ),
            description = "description $id",
            forksCount = id,
            stargazersCount = id + 1
        )
    }
}