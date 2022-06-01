package com.islam.music.features.album_details.domain.entites

import com.squareup.moshi.Json


data class Track(
    val artist: Artist,
    @field:Json(name = "@attr")
    val attr: Attr,
    val duration: Int, // 239
    val name: String, // Believe
    val streamable: Streamable,
    val url: String // https://www.last.fm/music/Cher/Believe/Believe
)