package com.books.inventory.dto

class BookRequest(
    val title: String,
    val authors: List<String>,
    val image: String,
    val description: String,
    val price: Double,
    val quantity: Int
)