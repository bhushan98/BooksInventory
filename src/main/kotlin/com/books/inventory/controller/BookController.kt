package com.books.inventory.controller

import com.books.inventory.dto.BookRequest
import com.books.inventory.dto.BookResponse
import com.books.inventory.service.BookService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController()
@RequestMapping("/api/book")
class BookController(private val bookService: BookService) {

    @PostMapping
    fun createBook(@RequestBody request: BookRequest): Mono<BookResponse> {
        return bookService.addBook(request)
            .map { BookResponse.fromEntity(it) }
    }

    @GetMapping
    fun findAllBooks(): Flux<BookResponse> {
        return bookService.findAll()
            .map { BookResponse.fromEntity(it) }
    }

    @GetMapping("{id}")
    fun findBookById(@PathVariable id: String): Mono<BookResponse> {
        return bookService.findById(id)
            .map { BookResponse.fromEntity(it) }
    }

    @GetMapping("/title/{title}")
    fun findBookByTitle(@PathVariable title: String): Flux<BookResponse> {
        return bookService.findByTitle(title)
            .map { BookResponse.fromEntity(it) }
    }

    @GetMapping("/author/{author}")
    fun findBookByAuthor(@PathVariable author: String): Flux<BookResponse> {
        return bookService.findByAuthor(author)
            .map { BookResponse.fromEntity(it) }
    }

    @PutMapping("{id}")
    fun updateBook(@PathVariable id: String, @RequestBody request: BookRequest): Mono<BookResponse> {
        return bookService.updateBook(id, request)
            .map { BookResponse.fromEntity(it) }
    }

    @DeleteMapping("{id}")
    fun deleteBook(@PathVariable id: String): Mono<Void> {
        return bookService.deleteById(id)
    }
}