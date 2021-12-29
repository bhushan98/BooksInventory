package com.books.inventory.dto

import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import java.util.stream.Collectors

class GoogleBookResponse (
    val title: String,
    val authors: List<String>,
    val image: String,
    val description: String,
    val price: Double,
){
    companion object {
        fun fromEntity(googleBookItem: GoogleBookItem): GoogleBookResponse =
            GoogleBookResponse(
                title = googleBookItem.volumeInfo?.title!!,
                authors = googleBookItem.volumeInfo.authors!!,
                image = googleBookItem.volumeInfo.imageLinks?.thumbnail!!,
                description = googleBookItem.volumeInfo?.description!!,
                price = googleBookItem.saleInfo?.listPrice?.amount!!
            )

        fun fromEntity(googleBookItem: List<GoogleBookItem>): Flux<GoogleBookResponse> {
            return googleBookItem.stream().map { fromEntity(it) }.toFlux()
        }
    }
}