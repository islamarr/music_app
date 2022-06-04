package com.islam.music.features.album_details.domain.entites

import com.squareup.moshi.Json


data class Track(
    val artist: Artist,
    @field:Json(name = "@attr")
    val attr: Attr,
    val duration: Int, // 239
    val name: String, // Believe
)

data class Attr(
    val rank: Int // 1
)