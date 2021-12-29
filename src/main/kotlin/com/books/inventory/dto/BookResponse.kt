package com.books.inventory.dto

import com.books.inventory.models.Book

class BookResponse(
    val id: String,
    val title: String,
    val authors: List<String>,
    val image: String,
    val description: String,
    val price: Double,
    val quantity: Int
) {
    companion object {
        fun fromEntity(book: Book): BookResponse =
            BookResponse(
                id = book.id!!,
                title = book.title,
                authors = book.authors,
                image = book.image,
                description = book.description,
                price = book.price,
                quantity = book.quantity
            )

    }
}