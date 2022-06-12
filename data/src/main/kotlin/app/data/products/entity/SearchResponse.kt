package app.data.products.entity

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<RepoResponse>,
    @SerializedName("total_count")
    val totalCount: Int,
)