package com.islam.music.features.album_details.domain.entites


import com.squareup.moshi.Json

data class Image(
    val size: String, // small
    @field:Json(name = "#text")
    val url: String // https://lastfm.freetls.fastly.net/i/u/34s/3b54885952161aaea4ce2965b2db1638.png
)