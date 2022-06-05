package com.islam.music.features.album_details.domain.entites

import com.squareup.moshi.Json


data class Track(
    val artist: Artist = Artist(),
    @field:Json(name = "@attr")
    val attr: Attr = Attr(),
    val duration: Int? = 0, // 239
    val name: String? = null, // Believe
)

data class Attr(
    val rank: Int? = 0 // 1
)