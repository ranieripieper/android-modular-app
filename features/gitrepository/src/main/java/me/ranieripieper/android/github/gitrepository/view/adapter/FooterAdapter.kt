package me.ranieripieper.android.github.gitrepository.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.ranieripieper.android.github.gitrepository.databinding.LayoutFooterRecyclerViewBinding
import me.ranieripieper.android.github.gitrepository.viewmodel.ViewState

class FooterAdapter : RecyclerView.Adapter<FooterViewHolder>() {

    private var state: ViewState = ViewState.Loading

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FooterViewHolder {

        val itemBinding =
            LayoutFooterRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return FooterViewHolder(
            itemBinding
        )
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: FooterViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val state: ViewState = state
            holder.bindView(state)
        }
    }

    fun updateState(viewState: ViewState) {
        state = viewState
        notifyDataSetChanged()
    }
}