package com.books.inventory.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document
data class Book (
    @Id
    val id: String? = null,

    @Field("title")
    var title: String,

    @Field("authors")
    var authors: List<String>,

    @Field("image")
    var image: String,

    @Field("description")
    var description: String,

    @Field("price")
    var price: Double,

    @Field("quantity")
    var quantity: Int
)