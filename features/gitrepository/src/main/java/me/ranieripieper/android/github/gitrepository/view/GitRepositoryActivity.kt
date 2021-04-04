package me.ranieripieper.android.github.gitrepository.view

import android.os.Bundle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ranieripieper.android.github.core.utils.InjectUtils
import me.ranieripieper.android.github.core.view.BaseActivity
import me.ranieripieper.android.github.gitrepository.databinding.ActivityRepositoryBinding
import me.ranieripieper.android.github.gitrepository.di.component.DaggerGitRepositoryComponent
import me.ranieripieper.android.github.gitrepository.view.adapter.FooterAdapter
import me.ranieripieper.android.github.gitrepository.view.adapter.GitRepositoryAdapter
import me.ranieripieper.android.github.gitrepository.viewmodel.GitRepositoryViewModel
import me.ranieripieper.android.github.gitrepository.viewmodel.ViewState

class GitRepositoryActivity : BaseActivity<GitRepositoryViewModel, ActivityRepositoryBinding>() {

    private lateinit var repositoryAdapter: GitRepositoryAdapter
    private lateinit var footerAdapter: FooterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

        viewModel.viewState.observe(this, { viewState ->
            renderViewState(viewState)
        })
    }

    private fun renderViewState(viewState: ViewState) {
        if (viewState is ViewState.Content) {
            repositoryAdapter.addItems(viewState.items)
            configScrollListener()
        }
        footerAdapter.updateState(viewState)
    }

    private fun initView() {
        repositoryAdapter = GitRepositoryAdapter()
        footerAdapter = FooterAdapter()
        binding.rvRepositories.apply {
            adapter = ConcatAdapter(repositoryAdapter, footerAdapter)
        }
    }

    private fun configScrollListener() {
        binding.rvRepositories.apply {
            addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int
                ) {
                    super.onScrolled(recyclerView, dx, dy)
                    val totalItemCount = layoutManager?.itemCount
                    val lastVisibleItem =
                        (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                    if (lastVisibleItem == totalItemCount?.minus(2)) {
                        clearOnScrollListeners()
                        viewModel.loadNextPage()
                    }
                }
            })
        }
    }

    override fun getViewBinding(): ActivityRepositoryBinding {
        return ActivityRepositoryBinding.inflate(layoutInflater)
    }

    override fun initializeDagger() {
        DaggerGitRepositoryComponent
            .builder()
            .coreComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)
    }

}