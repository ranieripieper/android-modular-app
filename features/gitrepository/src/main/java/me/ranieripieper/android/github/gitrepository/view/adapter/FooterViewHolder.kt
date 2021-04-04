package me.ranieripieper.android.github.gitrepository.view.adapter

import android.view.View
import me.ranieripieper.android.github.gitrepository.databinding.LayoutFooterRecyclerViewBinding
import me.ranieripieper.android.github.gitrepository.viewmodel.ViewState

class FooterViewHolder(private val itemBinding: LayoutFooterRecyclerViewBinding) :
    androidx.recyclerview.widget.RecyclerView.ViewHolder(itemBinding.root) {

    fun bindView(viewState: ViewState) {
        when (viewState) {
            is ViewState.Error -> {
                itemBinding.tvError.text = viewState.error
                itemBinding.tvError.visibility = View.VISIBLE
                itemBinding.loadingLayout.root.visibility = View.GONE
            }
            is ViewState.Loading -> {
                itemBinding.tvError.visibility = View.GONE
                itemBinding.loadingLayout.root.visibility = View.VISIBLE
            }
            is ViewState.Content -> {
                itemBinding.tvError.visibility = View.GONE
                itemBinding.loadingLayout.root.visibility = View.GONE
            }
            is ViewState.Empty -> {
                itemBinding.tvError.visibility = View.VISIBLE
                itemBinding.tvError.text = viewState.message
                itemBinding.loadingLayout.root.visibility = View.GONE
            }
        }
    }
}