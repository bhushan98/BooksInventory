package com.books.inventory.service

import com.books.inventory.dto.BookRequest
import com.books.inventory.models.Book
import com.books.inventory.repositories.BookRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.*

@SpringBootTest
class BookServiceTest {

    private val bookRepository: BookRepository = mock(BookRepository::class.java)

    lateinit var bookService: BookService

    val books = listOf(
        Book(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        ),
        Book(
            id = "2",
            title = "title1",
            authors = Arrays.asList("author2"),
            description = "description2",
            image = "image2",
            price = 103.0,
            quantity = 2
        ),
        Book(
            id = "3",
            title = "title1",
            authors = Arrays.asList("author3"),
            description = "description3",
            image = "image3",
            price = 102.0,
            quantity = 10
        ),
    )

    @BeforeEach
    fun setUp() {
        bookService = BookService(bookRepository)
    }

    @Test
    fun shouldReturnAllBooks() {
        `when`(bookRepository.findAll()).thenReturn(Flux.fromIterable(books))

        StepVerifier.create(bookService.findAll())
            .expectNextSequence(books)
            .verifyComplete()
    }

    @Test
    fun shouldFindBookById() {
        var book = Book(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        `when`(bookRepository.findById("1")).thenReturn(Mono.just(book))

        StepVerifier.create(bookService.findById("1"))
            .expectNextMatches { it.id == "1" }.verifyComplete()
    }

    @Test
    fun shouldFindBookByTitle() {
        var book = Book(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        `when`(bookRepository.findBookByTitle("title1")).thenReturn(Flux.just(book))

        StepVerifier.create(bookService.findByTitle("title1"))
            .expectNextMatches { it.title == book.title }.verifyComplete()
    }

    @Test
    fun shouldFindBookByAuthor() {
        var book = Book(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        `when`(bookRepository.findBookByAuthors("author1")).thenReturn(Flux.just(book))

        StepVerifier.create(bookService.findByAuthor("author1"))
            .expectNextMatches { it.authors == book.authors }.verifyComplete()
    }

    @Test
    @Disabled
    fun shouldDeleteBookById() {
        `when`(bookRepository.deleteById("1")).thenReturn(Mono.empty())

        StepVerifier.create(bookService.deleteById("1"))
            .verifyComplete()
    }


    @Test
    fun shouldUpdateBookById() {
        var book = Book(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        var updatedBook = Book(
            id = "1",
            title = "updatedtitle1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        var updatedBookRequest = BookRequest(
            title = "updatedtitle1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        `when`(bookRepository.findById("1")).thenReturn(Mono.just(book))
        `when`(bookRepository.save(updatedBook)).thenReturn(Mono.just(updatedBook))

        StepVerifier.create(bookService.updateBook("1", updatedBookRequest))
            .expectNext()
            .expectNextMatches {
                it == updatedBook
            }
    }


}