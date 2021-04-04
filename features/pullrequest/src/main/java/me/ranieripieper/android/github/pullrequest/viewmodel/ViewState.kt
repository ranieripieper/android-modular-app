package me.ranieripieper.android.github.pullrequest.viewmodel

sealed class ViewState {
    class Error(val error: String) : ViewState()
    object Loading : ViewState()
    class Empty(val message: String) : ViewState()
    class Content(val value: PullRequestPresentation) : ViewState()
}