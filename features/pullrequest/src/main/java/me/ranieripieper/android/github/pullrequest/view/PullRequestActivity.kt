package me.ranieripieper.android.github.pullrequest.view

import android.os.Bundle
import android.view.View
import me.ranieripieper.android.github.core.utils.InjectUtils
import me.ranieripieper.android.github.core.view.BaseActivity
import me.ranieripieper.android.github.features.navigation.BundleArguments
import me.ranieripieper.android.github.gitrepository.view.adapter.PullRequestAdapter
import me.ranieripieper.android.github.pullrequest.R
import me.ranieripieper.android.github.pullrequest.databinding.ActivityPullRequestBinding
import me.ranieripieper.android.github.pullrequest.di.component.DaggerPullRequestComponent
import me.ranieripieper.android.github.pullrequest.viewmodel.PullRequestViewModel
import me.ranieripieper.android.github.pullrequest.viewmodel.ViewState

class PullRequestActivity : BaseActivity<PullRequestViewModel, ActivityPullRequestBinding>() {

    private lateinit var pullRequestAdapter: PullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        viewModel.viewState.observe(this, { viewState ->
            renderViewState(viewState)
        })

        viewModel.fetchPullRequests(
            intent.extras?.getString(BundleArguments.ARG_OWNER, ""),
            getArgRepositoryName()
        )
    }

    private fun renderViewState(viewState: ViewState) {
        when (viewState) {
            is ViewState.Content -> {
                pullRequestAdapter.addItems(viewState.value.items)
                binding.apply {
                    tvOpen.text = viewState.value.openText
                    tvClose.text = viewState.value.closedText
                    loadingLayout.root.visibility = View.GONE
                    layoutHeader.visibility = View.VISIBLE
                    tvError.visibility = View.GONE
                }
            }
            is ViewState.Loading -> {
                binding.apply {
                    loadingLayout.root.visibility = View.VISIBLE
                    layoutHeader.visibility = View.GONE
                    tvError.visibility = View.GONE
                }
            }
            is ViewState.Empty -> {
                renderErrorOrMessage(viewState.message)
            }
            is ViewState.Error -> {
                renderErrorOrMessage(viewState.error)
            }
        }
    }

    private fun renderErrorOrMessage(text: String) {
        binding.apply {
            layoutHeader.visibility = View.GONE
            loadingLayout.root.visibility = View.GONE
            tvError.visibility = View.VISIBLE
            tvError.text = text
        }
    }

    private fun initView() {
        pullRequestAdapter = PullRequestAdapter()
        binding.rvRepositories.apply {
            adapter = pullRequestAdapter
        }

        binding.toolbar.title =
            getString(R.string.pull_requqest_toolbar_title, getArgRepositoryName())

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getArgRepositoryName(): String? {
        return intent.extras?.getString(BundleArguments.ARG_REPOSITORY, "")
    }

    override fun getViewBinding(): ActivityPullRequestBinding {
        return ActivityPullRequestBinding.inflate(layoutInflater)
    }

    override fun initializeDagger() {
        DaggerPullRequestComponent
            .builder()
            .coreComponent(InjectUtils.provideBaseComponent(applicationContext))
            .build()
            .inject(this)
    }
}