package app.domain.products.entity

class Repo(
    val id: Long,
    val fullName: String,
    val forksCount: Int?,
    val openIssuesCount: Int?,
    val stargazersCount: Int?,
    val description: String?,
    val language: String?,
    val ownerName: String?,
    val avatarUrl: String?,
)