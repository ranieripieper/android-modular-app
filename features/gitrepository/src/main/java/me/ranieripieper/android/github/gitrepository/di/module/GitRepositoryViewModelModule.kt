package me.ranieripieper.android.github.gitrepository.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.ranieripieper.android.github.core.di.module.ViewModelKey
import me.ranieripieper.android.github.gitrepository.viewmodel.GitRepositoryViewModel

@Module
abstract class GitRepositoryViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GitRepositoryViewModel::class)
    abstract fun bindRepositoryViewModel(viewModelGit: GitRepositoryViewModel): ViewModel
}

