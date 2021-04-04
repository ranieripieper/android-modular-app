package me.ranieripieper.android.github.pullrequest.view.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import me.ranieripieper.android.github.features.navigation.FeaturesNavigation
import me.ranieripieper.android.github.pullrequest.databinding.ItemCellPullrequestBinding
import me.ranieripieper.android.github.pullrequest.viewmodel.PullRequestItem

class PullRequestViewHolder(private val itemBinding: ItemCellPullrequestBinding) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemBinding.root) {

    fun bindView(
        pullRequestItem: PullRequestItem
    ) {
        itemBinding.tvRepoName.text = pullRequestItem.name
        itemBinding.tvRepoDescription.text = pullRequestItem.description
        itemBinding.tvUsername.text = pullRequestItem.username

        Glide.with(itemBinding.root.context)
            .load(pullRequestItem.avatarUrl)
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemBinding.ivUser)

        itemBinding.root.setOnClickListener {
            FeaturesNavigation.openBrowser(
                context = itemBinding.root.context,
                url = pullRequestItem.url
            )
        }
    }
}