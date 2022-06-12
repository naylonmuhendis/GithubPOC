package app.presentation.products.entity

import androidx.recyclerview.widget.RecyclerView
import app.presentation.databinding.ItemRepoListBinding

class RepoItemViewHolder(val itemBinding: ItemRepoListBinding) :
    RecyclerView.ViewHolder(itemBinding.parentView.rootView) {
    fun bind(repoUI: RepoUI) = with(itemView) {
        itemBinding.repoItem = repoUI
        //itemBinding.itemProductTypeTxv.text = beer.type
    }

}