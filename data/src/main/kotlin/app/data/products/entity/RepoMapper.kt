package app.data.products.entity

import app.domain.base.mapper.Mapper
import app.domain.products.entity.Repo
import app.domain.products.entity.RepoOwner

class RepoMapper : Mapper<RepoResponse, Repo> {

    override fun mapLeftToRight(obj: RepoResponse): Repo = with(obj) {
        Repo(
            id = id,
            fullName = name,
            forksCount = forks,
            openIssuesCount = openIssuesCount,
            stargazersCount = stargazersCount,
            description = description,
            language = language,
            ownerName = owner.login,
            avatarUrl = owner.avatarUrl
        )
    }

}