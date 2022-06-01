package com.islam.music.features.album_details.domain.entites


import com.squareup.moshi.Json

data class Streamable(
    val fulltrack: String, // 0
    @field:Json(name = "#text")
    val text: String // 0
)