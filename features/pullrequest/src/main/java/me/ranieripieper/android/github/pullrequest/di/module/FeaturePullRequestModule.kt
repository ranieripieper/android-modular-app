package me.ranieripieper.android.github.pullrequest.di.module

import dagger.Module
import dagger.Provides
import me.ranieripieper.android.github.pullrequest.data.remote.PullRequestApi
import me.ranieripieper.android.github.pullrequest.di.scopes.FeaturePullRequestScope
import retrofit2.Retrofit

@Module
class FeaturePullRequestModule {

    @Provides
    @FeaturePullRequestScope
    fun providePullRequestApi(retrofit: Retrofit): PullRequestApi =
        retrofit.create(PullRequestApi::class.java)

}