package app.presentation.products.virtualcv

import android.view.View
import app.presentation.base.adapter.BaseListAdapter
import app.presentation.base.adapter.RecyclerItem

class GalleryListAdapter(onItemClick: (RecyclerItem, View) -> Unit) : BaseListAdapter(
    GalleryCell,
    onItemClick = onItemClick
)