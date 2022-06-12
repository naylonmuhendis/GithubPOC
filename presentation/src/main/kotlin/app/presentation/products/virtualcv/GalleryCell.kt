package app.presentation.products.virtualcv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import app.presentation.R
import app.presentation.base.adapter.Cell
import app.presentation.base.adapter.RecyclerItem
import app.presentation.databinding.ItemGalleryBinding

object GalleryCell : Cell<RecyclerItem, ViewBinding> {

    override fun belongsTo(item: RecyclerItem?): Boolean {
        return item is GalleryUI
    }

    override fun type(): Int {
        return R.layout.item_gallery
    }

    override fun binding(parent: ViewGroup): ItemGalleryBinding {
        return ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun holder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ItemGalleryViewHolder(binding(parent))
    }

    override fun bind(
        holder: RecyclerView.ViewHolder,
        item: RecyclerItem?,
        onItemClick: ((RecyclerItem, View) -> Unit)?,
    ) {
        if (holder is ItemGalleryViewHolder && item is GalleryUI) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClick?.run {
                    this(item, holder.itemBinding.root)
                }
            }
        }
    }

}