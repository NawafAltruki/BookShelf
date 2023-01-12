package com.example.bookshelf.network

import com.example.bookshelf.database.DatabaseBooks
import com.example.bookshelf.domain.ModelBook
import com.example.bookshelf.domain.ModelBookF
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

// Google books Api
data class BooksApi(

    @Json(name = "totalItems")
    val totalItems: Int? = null,

    @Json(name = "kind")
    val kind: String? = null,

    @Json(name = "items")
    val items: List<ItemsItem?>? = null
)

data class ListPrice(

    @Json(name = "amount")
    val amount: Double? = null,

    @Json(name = "currencyCode")
    val currencyCode: String? = null,
)

data class ItemsItem(

    @Json(name = "saleInfo")
    val saleInfo: SaleInfo? = null,

    @Json(name = "searchInfo")
    val searchInfo: SearchInfo? = null,

    @Json(name = "kind")
    val kind: String? = null,

    @Json(name = "volumeInfo")
    val volumeInfo: VolumeInfo? = null,

    @Json(name = "etag")
    val etag: String? = null,

    @Json(name = "id")
    val id: String ,

    @Json(name = "accessInfo")
    val accessInfo: AccessInfo? = null,

    @Json(name = "selfLink")
    val selfLink: String? = null
)

data class SearchInfo(

    @Json(name = "textSnippet")
    val textSnippet: String? = null
)

data class RetailPrice(

    @Json(name = "amount")
    val amount: Any? = null,

    @Json(name = "currencyCode")
    val currencyCode: String? = null,

    @Json(name = "amountInMicros")
    val amountInMicros: Int? = null
)

data class SaleInfo(

    @Json(name = "offers")
    val offers: List<OffersItem?>? = null,

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "isEbook")
    val isEbook: Boolean? = null,

    @Json(name = "saleability")
    val saleability: String? = null,

    @Json(name = "buyLink")
    val buyLink: String? = null,

    @Json(name = "retailPrice")
    val retailPrice: RetailPrice? = null,

    @Json(name = "listPrice")
    val listPrice: ListPrice? = null
)

data class Epub(

    @Json(name = "isAvailable")
    val isAvailable: Boolean? = null,

    @Json(name = "acsTokenLink")
    val acsTokenLink: String? = null,

    @Json(name = "downloadLink")
    val downloadLink: String? = null
)

data class VolumeInfo(

    @Json(name = "industryIdentifiers")
    val industryIdentifiers: List<IndustryIdentifiersItem?>? = null,

    @Json(name = "pageCount")
    val pageCount: Int? = null,

    @Json(name = "printType")
    val printType: String? = null,

    @Json(name = "readingModes")
    val readingModes: ReadingModes? = null,

    @Json(name = "previewLink")
    val previewLink: String? = null,

    @Json(name = "canonicalVolumeLink")
    val canonicalVolumeLink: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "language")
    val language: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "imageLinks")
    val imageLinks: ImageLinks? = null,

    @Json(name = "subtitle")
    val subtitle: String? = null,

    @Json(name = "averageRating")
    val averageRating: Int? = null,

    @Json(name = "panelizationSummary")
    val panelizationSummary: PanelizationSummary? = null,

    @Json(name = "publisher")
    val publisher: String? = null,

    @Json(name = "ratingsCount")
    val ratingsCount: Int? = null,

    @Json(name = "publishedDate")
    val publishedDate: String? = null,

    @Json(name = "categories")
    val categories: List<String?>? = null,

    @Json(name = "maturityRating")
    val maturityRating: String? = null,

    @Json(name = "allowAnonLogging")
    val allowAnonLogging: Boolean? = null,

    @Json(name = "contentVersion")
    val contentVersion: String? = null,

    @field:SerializedName("authors")
    val authors: List<String?>? = null,

    @Json(name = "infoLink")
    val infoLink: String? = null
)

data class ImageLinks(

    @Json(name = "thumbnail")
    val thumbnail: String? = null,

    @Json(name = "smallThumbnail")
    val smallThumbnail: String? = null
)

data class IndustryIdentifiersItem(

    @Json(name = "identifier")
    val identifier: String? = null,

    @Json(name = "type")
    val type: String? = null
)

data class ReadingModes(

    @Json(name = "image")
    val image: Boolean? = null,

    @Json(name = "text")
    val text: Boolean? = null
)

data class Pdf(

    @Json(name = "isAvailable")
    val isAvailable: Boolean? = null,

    @Json(name = "acsTokenLink")
    val acsTokenLink: String? = null,

    @Json(name = "downloadLink")
    val downloadLink: String? = null
)

data class OffersItem(

    @Json(name = "finskyOfferType")
    val finskyOfferType: Int? = null,

    @Json(name = "retailPrice")
    val retailPrice: RetailPrice? = null,

    @Json(name = "listPrice")
    val listPrice: ListPrice? = null
)

data class PanelizationSummary(

    @Json(name = "containsImageBubbles")
    val containsImageBubbles: Boolean? = null,

    @Json(name = "containsEpubBubbles")
    val containsEpubBubbles: Boolean? = null
)

data class AccessInfo(

    @Json(name = "accessViewStatus")
    val accessViewStatus: String? = null,

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "viewability")
    val viewability: String? = null,

    @Json(name = "pdf")
    val pdf: Pdf? = null,

    @Json(name = "webReaderLink")
    val webReaderLink: String? = null,

    @Json(name = "epub")
    val epub: Epub? = null,

    @Json(name = "publicDomain")
    val publicDomain: Boolean? = null,

    @Json(name = "quoteSharingAllowed")
    val quoteSharingAllowed: Boolean? = null,

    @Json(name = "embeddable")
    val embeddable: Boolean? = null,

    @Json(name = "textToSpeechPermission")
    val textToSpeechPermission: String? = null
)

/**
 * Convert Network results to database objects
 */
fun BooksApi.asDomainModel(): List<ModelBookF>? {
    return items?.map {
        ModelBookF(
            id = it!!.id,
            title = it?.volumeInfo?.title,
            subtitle = it?.volumeInfo?.subtitle,
            author = it?.volumeInfo?.authors.toString(),
            price = it?.saleInfo?.listPrice?.amount,
            lang = it?.volumeInfo?.language,
            pages = it?.volumeInfo?.pageCount,
            publisher = it?.volumeInfo?.publisher,
            ratingCount = it?.volumeInfo?.ratingsCount,
            averageRating = it?.volumeInfo?.averageRating,
            publisherDate = it?.volumeInfo?.publishedDate,
            description = it?.volumeInfo?.description,
            thumbnail = it?.volumeInfo?.imageLinks?.thumbnail,
            info = it?.volumeInfo?.infoLink,
            wepReader = it?.accessInfo?.webReaderLink)
    }
}

/**
 * Convert Network results to database objects
 */
fun BooksApi.asDatabaseModel(): List<DatabaseBooks>? {
    return items?.map {
        DatabaseBooks(
            id = it!!.id,
            title = it?.volumeInfo?.title,
            subtitle = it?.volumeInfo?.subtitle,
            author = it?.volumeInfo?.authors.toString(),
            price = it?.saleInfo?.listPrice?.amount,
            lang = it?.volumeInfo?.language,
            pages = it?.volumeInfo?.pageCount,
            publisher = it?.volumeInfo?.publisher,
            ratingCount = it?.volumeInfo?.ratingsCount,
            averageRating = it?.volumeInfo?.averageRating,
            publisherDate = it?.volumeInfo?.publishedDate,
            description = it?.volumeInfo?.description,
            thumbnail = it?.volumeInfo?.imageLinks?.thumbnail,
            info = it?.volumeInfo?.infoLink,
            wepReader = it?.accessInfo?.webReaderLink)
    }
}
