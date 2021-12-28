package com.books.inventory.service

import com.books.inventory.dto.BookRequest
import com.books.inventory.exception.NotFoundException
import com.books.inventory.models.Book
import com.books.inventory.repositories.BookRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BookService (private val bookRepository: BookRepository) {

    fun addBook(request: BookRequest): Mono<Book> =
        bookRepository.save(
            Book(
                title = request.title,
                authors = request.authors,
                image = request.image,
                description = request.description,
                price = request.price,
                quantity = request.quantity
            )
        )

    fun findAll(): Flux<Book> =
        bookRepository.findAll()

    fun findById(id: String): Mono<Book> =
        bookRepository.findById(id)
            .switchIfEmpty(
                Mono.error(
                    NotFoundException("Book with ID: $id not found !")
                )
            )

    fun updateBook(id: String, request: BookRequest): Mono<Book> =
        findById(id)
            .flatMap {
                bookRepository.save(
                    it.apply {
                        title = request.title
                        authors = request.authors
                        image = request.image
                        description = request.description
                        price = request.price
                        quantity = request.quantity
                    }
                )
            }

    fun deleteById(id: String): Mono<Void> =
        findById(id)
            .flatMap(bookRepository::delete)

}