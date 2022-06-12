package app.presentation.products.entity.repo

import app.domain.base.mapper.Mapper
import app.domain.products.entity.Repo

class RepoMapper : Mapper<Repo, RepoUI> {

    override fun mapLeftToRight(obj: Repo): RepoUI = with(obj) {
        RepoUI(
            id = id,
            fullName = fullName,
            forksCount = forksCount,
            openIssuesCount = openIssuesCount,
            stargazersCount = stargazersCount,
            language = language,
            ownerName = ownerName,
            avatarUrl = avatarUrl,
            description = description,
            updatedAt = updatedAt
        )
    }

}
