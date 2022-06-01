package com.islam.music.features.album_details.domain.entites


import com.squareup.moshi.Json

data class AlbumDetails(  //TODO unify duplicated models
    val artist: String, // Cher
    @field:Json(name = "image")
    val images: List<Image>,
    val listeners: String, // 553031
    @field:Json(name = "mbid")
    val id: String, // 03c91c40-49a6-44a7-90e7-a700edf97a62
    val name: String, // Believe
    val playcount: String, // 4025524
    val tags: Tags,
    val tracks: Tracks,
    val url: String, // https://www.last.fm/music/Cher/Believe
    val wiki: Wiki
)