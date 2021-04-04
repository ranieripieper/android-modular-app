package me.ranieripieper.android.github.pullrequest.di.component

import dagger.Component
import me.ranieripieper.android.github.core.di.component.CoreComponent
import me.ranieripieper.android.github.pullrequest.di.module.FeaturePullRequestModule
import me.ranieripieper.android.github.pullrequest.di.module.PullRequestViewModelModule
import me.ranieripieper.android.github.pullrequest.di.scopes.FeaturePullRequestScope
import me.ranieripieper.android.github.pullrequest.view.PullRequestActivity

@FeaturePullRequestScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        PullRequestViewModelModule::class,
        FeaturePullRequestModule::class,
    ]
)
interface PullRequestComponent {

    fun inject(activity: PullRequestActivity)

}