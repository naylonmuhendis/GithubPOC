package app.presentation.products.entity.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import app.presentation.R
import app.presentation.base.adapter.Cell
import app.presentation.base.adapter.RecyclerItem
import app.presentation.databinding.ItemRepoListBinding

object RepoItemCell : Cell<RecyclerItem, ViewBinding> {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is RepoUI
    }

    override fun type(): Int {
        return R.layout.item_repo_list
    }

    override fun binding(parent: ViewGroup): ItemRepoListBinding {
        return ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return RepoItemViewHolder(binding(parent))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        onItemClick: ((RecyclerItem, View) -> Unit)?,
    ) {
        if (holder is RepoItemViewHolder && item is RepoUI) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClick?.run {
                    this(item, holder.itemBinding.parentView)
                }
            }
        }
    }

}