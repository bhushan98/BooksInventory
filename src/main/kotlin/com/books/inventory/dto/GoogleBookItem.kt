package com.books.inventory.dto

class GoogleBookItem (
    val kind: String?,
    val id: String?,
    val etag: String?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?,
    val saleInfo: SaleInfo?,
    val accessInfo: AccessInfo?,
    val searchInfo: SearchInfo?
)

class SearchInfo (
    val textSnippet: String?
        )

class AccessInfo (
    val country: String?,
    val viewability: String?,
    val embeddable: Boolean?,
    val publicDomain: Boolean?,
    val textToSpeechPermission: String?,
    val epub: Epub?,
    val pdf: Pdf?,
    val webReaderLink: String?,
    val accessViewStatus: String?,
    val quoteSharingAllowed: Boolean?
)

class Pdf (
    val isAvailable: Boolean?
)

class Epub (
    val isAvailable: Boolean?
)

class SaleInfo (
    val country: String?,
    val saleability: String?,
    val isEbook: Boolean?,
    val listPrice: ListPrice?,
)

class ListPrice (
    val amount: Double?,
    val currencyCode: String?
    )

class VolumeInfo (
    val title: String?,
    val authors: List<String>?,
    val publishDate: String?,
    val description: String?,
    val readingModes: ReadingModes?,
    val printType: String?,
    val maturityRating: String?,
    val allowedAnonLogging: Boolean?,
    val contentVersion: String?,
    val penalizationSummary: PenalizationSummary?,
    val imageLinks: ImageLinks?,
    val language: String?,
    val previewLink: String?,
    val infoLink: String?,
    val canonicalVolumeLink: String?
)

class ImageLinks (
    val smallThumbnail: String?,
    val thumbnail: String?
    )

class PenalizationSummary (
    val containsEpubBubbles: Boolean?,
    val containsImageBubbles: Boolean?
    )

class ReadingModes (
    val text: Boolean?,
    val image: Boolean?
    )
