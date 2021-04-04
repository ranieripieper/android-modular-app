package me.ranieripieper.android.github.gitrepository.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.ranieripieper.android.github.pullrequest.databinding.ItemCellPullrequestBinding
import me.ranieripieper.android.github.pullrequest.view.adapter.PullRequestViewHolder
import me.ranieripieper.android.github.pullrequest.viewmodel.PullRequestItem

class PullRequestAdapter() :
    RecyclerView.Adapter<PullRequestViewHolder>() {

    private var items = mutableListOf<PullRequestItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val itemBinding =
            ItemCellPullrequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PullRequestViewHolder(
            itemBinding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val gitRepositoryPresentation: PullRequestItem = items[position]
            holder.bindView(gitRepositoryPresentation)
        }
    }

    // add items
    fun addItems(values: List<PullRequestItem>) {
        val currentSize = items.size
        items.addAll(values)
        notifyItemRangeChanged(currentSize, items.size)
        notifyDataSetChanged()
    }
}