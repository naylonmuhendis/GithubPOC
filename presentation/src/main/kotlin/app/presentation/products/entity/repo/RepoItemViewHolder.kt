package app.presentation.products.entity.repo

import androidx.recyclerview.widget.RecyclerView
import app.presentation.databinding.ItemRepoListBinding

class RepoItemViewHolder(val itemBinding: ItemRepoListBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(repoUI: RepoUI) = with(itemView) {
        itemBinding.repoItem = repoUI
        //itemBinding.itemProductTypeTxv.text = beer.type
    }

}