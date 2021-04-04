package me.ranieripieper.android.github.gitrepository.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.ranieripieper.android.github.gitrepository.databinding.ItemCellRepositoryBinding
import me.ranieripieper.android.github.gitrepository.viewmodel.GitRepositoryPresentation

class GitRepositoryAdapter() :
    RecyclerView.Adapter<GitRepositoryViewHolder>() {

    private var items = mutableListOf<GitRepositoryPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoryViewHolder {
        val itemBinding =
            ItemCellRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GitRepositoryViewHolder(
            itemBinding
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GitRepositoryViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val gitRepositoryPresentation: GitRepositoryPresentation = items[position]
            holder.bindView(gitRepositoryPresentation)
        }
    }

    // Update data
    fun addItems(values: List<GitRepositoryPresentation>) {
        val currentSize = items.size
        items.addAll(values)
        notifyItemRangeChanged(currentSize, items.size)
        notifyDataSetChanged()
    }
}