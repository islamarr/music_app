package com.islam.music.features.top_albums.domain.entites


import com.squareup.moshi.Json

data class Image(
    val size: String, // small
    @field:Json(name = "#text")
    val url: String // https://lastfm.freetls.fastly.net/i/u/34s/2a96cbd8b46e442fc41c2b86b821562f.png
)