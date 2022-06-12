package app.presentation.products.entity.repo

import android.os.Parcelable
import app.presentation.base.adapter.RecyclerItem
import app.presentation.extension.tramsTimeAgo
import app.presentation.extension.transTimeStamp
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
    val updatedAt: String,
) : RecyclerItem, Parcelable {
    val startGazerCountString: String
        get() {
            return stargazersCount.toString()
        }

    val updatedAtFormatted: String
        get() {
            return updatedAt.tramsTimeAgo()
        }


}