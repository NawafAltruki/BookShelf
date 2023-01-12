package com.example.bookshelf.domain

data class ModelBook(
    val id: String,
    val title: String?,
    val subtitle: String?,
    val author: String,
    val price: Double?,
    val lang: String?,
    val pages: Int?,
    val publisher: String?,
    val ratingCount: Int?,
    val averageRating: Int?,
    val publisherDate: String?,
    val description: String?,
    val thumbnail: String?,
    val info: String?,
    val wepReader: String?,
    var favorite: Boolean?
)

data class ModelBookF(
    val id: String,
    val title: String?,
    val subtitle: String?,
    val author: String,
    val price: Double?,
    val lang: String?,
    val pages: Int?,
    val publisher: String?,
    val ratingCount: Int?,
    val averageRating: Int?,
    val publisherDate: String?,
    val description: String?,
    val thumbnail: String?,
    val info: String?,
    val wepReader: String?
)