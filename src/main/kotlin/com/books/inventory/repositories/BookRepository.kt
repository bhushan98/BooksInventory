package com.books.inventory.repositories

import com.books.inventory.models.Book
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface BookRepository : ReactiveMongoRepository<Book, String> {
    fun findBookByTitle(title: String): Flux<Book>

    fun findBookByAuthors(author: String): Flux<Book>

}