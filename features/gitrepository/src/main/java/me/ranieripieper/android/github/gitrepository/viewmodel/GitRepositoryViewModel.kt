package me.ranieripieper.android.github.gitrepository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.ranieripieper.android.github.core.utils.Constants
import me.ranieripieper.android.github.core.viewmodel.BaseViewModel
import me.ranieripieper.android.github.gitrepository.data.repository.GitRepositoryRepository
import me.ranieripieper.android.github.gitrepository.viewmodel.converter.GitRespositoryConverter
import javax.inject.Inject

class GitRepositoryViewModel @Inject internal constructor(
    private val repository: GitRepositoryRepository,
    private val converter: GitRespositoryConverter
) : BaseViewModel() {

    companion object {
        const val TAG = "GitRepositoryViewModel"
    }

    private var lastRequestedPage = 1
    private val query = "language:java"

    private val _viewState: MutableLiveData<ViewState> by lazy {
        val liveData = MutableLiveData<ViewState>()

        searchRepositories(query = query, viewStateLiveData = liveData)

        return@lazy liveData
    }

    val viewState: LiveData<ViewState> = _viewState

    fun loadNextPage() {
        searchRepositories(query, lastRequestedPage + 1)
    }

    private fun searchRepositories(
        query: String,
        page: Int = Constants.API_FIRST_PAGE,
        viewStateLiveData: MutableLiveData<ViewState> = _viewState
    ) {
        viewStateLiveData.value = ViewState.Loading
        disposable.add(
            repository.searchRepositories(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        lastRequestedPage++
                        viewStateLiveData.value = converter.convertGitRepositories(response)
                    },
                    { error ->
                        viewStateLiveData.value = converter.getViewStateError(error)
                    })
        )
    }
}