package com.books.inventory.integration

import com.books.inventory.dto.BookRequest
import com.books.inventory.models.Book
import com.books.inventory.repositories.BookRepository
import com.books.inventory.service.BookService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import reactor.test.StepVerifier
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class BookIntegrationTest {

    @Autowired
    lateinit var bookService: BookService

    @Autowired
    lateinit var bookRepository: BookRepository

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
    fun init() {
        bookRepository.saveAll(books)
    }

    @Test
    fun shouldReturnAllBooks() {
        StepVerifier.create(bookService.findAll())
            .expectNextSequence(books)
    }

    @Test
    fun shouldBeAbleToAddNewBook() {
        var book = Book(
            title = "New Added Book Title",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )

        bookRepository.save(book).block()

        StepVerifier.create(bookService.findByTitle(book.title))
            .expectNextMatches { it.title == book.title }
            .verifyComplete()

    }

    @Test
    fun shouldBeAbleToDeleteBookById() {
        var toBeDeleted = Book(
            id = "4",
            title = "New Added Book Title",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        bookRepository.save(toBeDeleted).block()

        StepVerifier.create(bookService.deleteById("4"))
            .expectNext()
            .verifyComplete()

        StepVerifier.create(bookService.findById("4"))
            .expectError()

    }

    @Test
    fun shouldBeAbleToUpdatePriceOfBook() {
        var toBeModified = Book(
            id = "4",
            title = "New Added Book Title",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        bookRepository.save(toBeModified).block()

        var modifiedRequest = BookRequest(
            title = "New Added Book Title",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 200.0,
            quantity = 1
        )

        bookService.updateBook(toBeModified.id!!, modifiedRequest).block()

        StepVerifier.create(bookService.findById(toBeModified.id!!))
            .expectNextMatches { it.price == 200.0 }
            .verifyComplete()
    }


}