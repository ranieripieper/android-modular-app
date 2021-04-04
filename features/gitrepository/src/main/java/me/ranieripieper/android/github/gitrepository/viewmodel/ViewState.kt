package me.ranieripieper.android.github.gitrepository.viewmodel

sealed class ViewState {
    class Error(val error: String) : ViewState()
    object Loading : ViewState()
    class Empty(val message: String) : ViewState()
    class Content(val items: List<GitRepositoryPresentation>) : ViewState()
}