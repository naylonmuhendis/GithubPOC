package app.presentation.products.entity.repo

import android.os.Parcelable
import app.presentation.base.adapter.RecyclerItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepoUI(
    override val id: Long,
    val fullName: String?,
    val forksCount: Int?,
    val openIssuesCount: Int?,
    val stargazersCount: Int?,
    val description: String?,
    val language: String?,
    val ownerName: String?,
    val avatarUrl: String?,
) : RecyclerItem, Parcelable