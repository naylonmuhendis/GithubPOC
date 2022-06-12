package app.presentation.products.repolist

import android.view.View
import app.presentation.base.adapter.BasePagedListAdapter
import app.presentation.base.adapter.RecyclerItem
import app.presentation.products.entity.RepoItemCell

class RepoListAdapter(onItemClick: (RecyclerItem, View) -> Unit) : BasePagedListAdapter(
    RepoItemCell,
    onItemClick = onItemClick
)