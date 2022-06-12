package app.presentation.products.virtualcv

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import app.presentation.databinding.ItemGalleryBinding
import app.presentation.databinding.ItemRepoListBinding
import app.presentation.products.entity.repo.RepoUI

class ItemGalleryViewHolder(val itemBinding: ItemGalleryBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(repoUI: GalleryUI) = with(itemView) {
        itemBinding.galleryIv.setImageDrawable(AppCompatResources.getDrawable(itemView.context,
            repoUI.drawable))
    }
}