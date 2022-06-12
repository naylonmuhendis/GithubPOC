package app.data.products.factory

import app.data.products.entity.RepoOwner
import app.data.products.entity.RepoResponse
import kotlin.random.Random

class ProductResponseFactory {

    companion object {

        fun createProducts(count: Int): List<RepoResponse> {
            return (1..count).map {
                RepoResponse(
                    id = it.toLong(),
                    "nodeId$it",
                    "name$it",
                    "fullName$it",
                    it % 2 == 0,
                    getRepoOwner(it),
                    "htmlUrl$it",
                    "description$it",
                    it % 2 == 0,
                    "url$it",
                    "forkUrl$it",
                    "keysUrl$it",
                    "collabaratorsUrl$it",
                    "teamsUrl$it",
                    "hooksUrl$it",
                    "issueEventsUrl$it",
                    "eventUrl$it",
                    "assigneesUrl$it",
                    "branchUrl$it",
                    "tagsUrl$it",
                    "blobsUrl$it",
                    "gitTagsUrl$it",
                    "gitRefUrl$it",
                    "treeUrl$it",
                    "statusesUrl$it",
                    "languagesUrl$it",
                    "stargazersUrl$it",
                    "contributorsUrl$it",
                    "subscribersUrl$it",
                    "subscriptionUrl$it",
                    "commitsUrl$it",
                    "gitCommitsUrl$it",
                    "commentsUrl$it",
                    "issueCommentUrl$it",
                    "contentsUrl$it",
                    "compareUrl$it",
                    "mergesUrl$it",
                    "archiveUrl$it",
                    "downloadsUrl$it",
                    "issuesUrl$it",
                    "pullsUrl$it",
                    "milesStonesUrl$it",
                    "notificationUrl$it",
                    "labelsUrl$it",
                    "releasesUrl$it",
                    "issueCommentUrl$it",
                    "createdAt$it",
                    "updatedAt$it",
                    "pushedAt$it",
                    "gitUrl$it",
                    "sshUrl$it",
                    "colneUrl$it",
                    "svnUrl$it",
                    "homepage$it",
                    size = it,
                    forks = it,
                    language = "language$it",
                    openIssuesCount = Random(0).nextInt(),
                    stargazersCount = Random(0).nextInt()
                )
            }
        }


        fun getRepoOwner(index: Int): RepoOwner {
            return RepoOwner(
                login = "login$index",
                index,
                "nodeId$index",
                "avatarUrl$index",
                "gravatarId$index",
                "url$index",
                "htmlUrl$index",
                "followersUrl$index",
                "followingUrl$index",
                "gistsUrl$index",
                "starredUrl$index",
                "subscriptionsUrl$index",
                "organizationsUrl$index",
                "reposUrl$index",
                "eventsUrl$index",
                "receivedEventsUrl$index",
                "type$index",
                "siteaAdmin$index",
            )
        }
    }
}
