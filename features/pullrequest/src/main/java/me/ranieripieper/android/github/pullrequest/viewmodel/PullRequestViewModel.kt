package me.ranieripieper.android.github.pullrequest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.ranieripieper.android.github.core.viewmodel.BaseViewModel
import me.ranieripieper.android.github.core.viewmodel.ResourceManager
import me.ranieripieper.android.github.pullrequest.data.repository.PullRequestRepository
import me.ranieripieper.android.github.pullrequest.viewmodel.converter.PullRequestConverter
import me.ranieripieper.android.github.ui.R
import javax.inject.Inject

class PullRequestViewModel @Inject internal constructor(
    private val repository: PullRequestRepository,
    private val converter: PullRequestConverter,
    private val resourceManager: ResourceManager
) : BaseViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun fetchPullRequests(
        owner: String?,
        repositoryName: String?,
        viewStateLiveData: MutableLiveData<ViewState> = _viewState
    ) {

        if (owner == null || repositoryName == null || owner.isEmpty() || repositoryName.isEmpty()) {
            viewStateLiveData.value =
                ViewState.Error(resourceManager.getString(R.string.default_error_message))
        } else {
            viewStateLiveData.value = ViewState.Loading
            disposable.add(
                repository.getPullRequests(owner, repositoryName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { response ->
                            viewStateLiveData.value = converter.convertPullRequests(response)
                        },
                        { error ->
                            viewStateLiveData.value = converter.getViewStateError(error)
                        })
            )
        }
    }
}