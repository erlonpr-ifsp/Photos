package com.github.erlonprifsp.photos.model

data class PhotoListItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
){
    override fun toString(): String {
        return title
    }
}