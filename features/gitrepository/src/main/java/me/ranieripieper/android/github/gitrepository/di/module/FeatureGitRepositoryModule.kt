package me.ranieripieper.android.github.gitrepository.di.module

import dagger.Module
import dagger.Provides
import me.ranieripieper.android.github.gitrepository.data.remote.GitRepositoryApi
import me.ranieripieper.android.github.gitrepository.di.scopes.FeatureGitRespositoryScope
import retrofit2.Retrofit

@Module
class FeatureGitRepositoryModule {

    @Provides
    @FeatureGitRespositoryScope
    fun provideGitRepositoryApi(retrofit: Retrofit): GitRepositoryApi =
        retrofit.create(GitRepositoryApi::class.java)

}