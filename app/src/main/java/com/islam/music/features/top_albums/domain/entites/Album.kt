package com.islam.music.features.top_albums.domain.entites

import com.squareup.moshi.Json


data class Album(
    val artist: Artist,
    @field:Json(name = "image")
    val images: List<Image>,
    @field:Json(name = "mbid")
    val id: String, // 63b3a8ca-26f2-4e2b-b867-647a6ec2bebd
    val name: String, // Believe
    val playcount: Int, // 4023419
    val url: String // https://www.last.fm/music/Cher/Believe
)