package app.presentation.products.productlist

import android.view.View
import app.presentation.base.adapter.BasePagedListAdapter
import app.presentation.base.adapter.RecyclerItem
import app.presentation.products.entity.BeerCell

class ProductsListAdapter(onItemClick: (RecyclerItem, View) -> Unit) : BasePagedListAdapter(
    BeerCell,
    onItemClick = onItemClick
)