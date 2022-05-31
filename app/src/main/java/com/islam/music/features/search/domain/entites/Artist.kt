package com.islam.music.features.search.domain.entites

import com.squareup.moshi.Json


data class Artist(
    @field:Json(name = "image")
    val images: List<Image>,
    val listeners: String, // 1378889 //TODO remove unused attributes in all data class
    @field:Json(name = "mbid")
    val id: String, // bfcc6d75-a6a5-4bc6-8282-47aec8531818
    @field:Json(name = "name")
    val name: String, // Cher
    val streamable: String, // 0
    val url: String // https://www.last.fm/music/Cher
)