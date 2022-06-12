package app.domain.products.factory

import app.domain.products.entity.Repo
import app.domain.products.entity.RepoOwner
import kotlin.random.Random

class RepoFactory {

    companion object {

        fun createProducts(count: Int): List<Repo> {
            return (1..count).map {
                Repo(
                    id = it.toLong(),
                    fullName = "name:$it",
                    forksCount = it,
                    openIssuesCount = it + 1,
                    stargazersCount = it + 2,
                    description = "description:$it",
                    language = "language$it",
                    ownerName = "ownerName$it",
                    avatarUrl = "https://images.punkapi.com/v2/$it.png",
                    updatedAt = Random(151515151).nextLong().toString()
                )
            }
        }
    }


}