package com.example.bookshelf.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookshelf.domain.ModelBook
@Entity
data class DatabaseBooks(
    @PrimaryKey
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
    var favorite: Boolean = false)



fun List<DatabaseBooks>.asDomainModel(): List<ModelBook> {
    return map {
        ModelBook(
            id = it.id,
            title = it.title,
            subtitle = it.subtitle,
            author = it.author,
            price = it.price,
            lang = it.lang,
            pages = it.pages,
            publisher = it.publisher,
            ratingCount = it.ratingCount,
            averageRating = it.averageRating,
            publisherDate = it.publisherDate,
            description = it.description,
            thumbnail = it.thumbnail,
            info = it.info,
            wepReader = it.wepReader,
            favorite = it.favorite)
    }
}
