package com.islam.music.features.search.domain.entites


import com.squareup.moshi.Json

data class Results(
    @field:Json(name = "artistmatches")
    val artists: Artistmatches,
    @field:Json(name = "@attr")
    val attr: Attr,
    @field:Json(name = "opensearch:itemsPerPage")
    val opensearchItemsPerPage: String, // 30
    @field:Json(name = "opensearch:Query")
    val opensearchQuery: OpensearchQuery,
    @field:Json(name = "opensearch:startIndex")
    val opensearchStartIndex: String, // 0
    @field:Json(name = "opensearch:totalResults")
    val opensearchTotalResults: String // 73593
)