package app.presentation.products.factory

import app.presentation.products.entity.repo.RepoUI
import kotlin.random.Random

class RepoUIFactory {

    companion object {

        fun createProducts(count: Int): List<RepoUI> {
            return (1..count).map {
                RepoUI(
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