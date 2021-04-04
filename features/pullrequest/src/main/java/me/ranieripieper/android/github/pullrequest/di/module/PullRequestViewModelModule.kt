package me.ranieripieper.android.github.pullrequest.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.ranieripieper.android.github.core.di.module.ViewModelKey
import me.ranieripieper.android.github.pullrequest.viewmodel.PullRequestViewModel

@Module
abstract class PullRequestViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PullRequestViewModel::class)
    abstract fun bindRepositoryViewModel(viewModelGit: PullRequestViewModel): ViewModel
}

