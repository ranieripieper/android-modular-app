package me.ranieripieper.android.github.gitrepository.di.component

import dagger.Component
import me.ranieripieper.android.github.core.di.component.CoreComponent
import me.ranieripieper.android.github.gitrepository.di.module.FeatureGitRepositoryModule
import me.ranieripieper.android.github.gitrepository.di.module.GitRepositoryViewModelModule
import me.ranieripieper.android.github.gitrepository.di.scopes.FeatureGitRespositoryScope
import me.ranieripieper.android.github.gitrepository.view.GitRepositoryActivity

@FeatureGitRespositoryScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        GitRepositoryViewModelModule::class,
        FeatureGitRepositoryModule::class,
    ]
)
interface GitRepositoryComponent {

    fun inject(activityGit: GitRepositoryActivity)

}