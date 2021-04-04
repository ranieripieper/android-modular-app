package me.ranieripieper.android.github.gitrepository.view.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import me.ranieripieper.android.github.features.navigation.FeaturesNavigation
import me.ranieripieper.android.github.gitrepository.databinding.ItemCellRepositoryBinding
import me.ranieripieper.android.github.gitrepository.viewmodel.GitRepositoryPresentation

class GitRepositoryViewHolder(private val itemBinding: ItemCellRepositoryBinding) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemBinding.root) {

    fun bindView(
        gitRepository: GitRepositoryPresentation
    ) {
        itemBinding.tvRepoName.text = gitRepository.name
        itemBinding.tvRepoDescription.text = gitRepository.description
        itemBinding.tvUsername.text = gitRepository.username
        itemBinding.tvFork.text = gitRepository.forks
        itemBinding.tvStars.text = gitRepository.stars

        Glide.with(itemBinding.root.context)
            .load(gitRepository.avatarUrl)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemBinding.ivUser)

        itemBinding.root.setOnClickListener {
            FeaturesNavigation.startPullRequestModule(
                context = itemBinding.root.context,
                gitRepository.username,
                gitRepository.name
            )
        }
    }
}