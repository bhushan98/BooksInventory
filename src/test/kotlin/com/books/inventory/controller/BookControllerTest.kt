package com.books.inventory.controller

import com.books.inventory.dto.BookResponse
import com.books.inventory.models.Book
import com.books.inventory.service.BookService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.test.web.reactive.server.expectBodyList
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@WebFluxTest
class BookControllerTest {
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

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockBean
    lateinit var bookService: BookService

    @Test
    fun shouldListAllBooks() {
        `when`(bookService.findAll()).thenReturn(Flux.fromIterable(books))

        webTestClient.get()
            .uri("/api/book")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<BookResponse>()
            .hasSize(3)
    }

    @Test
    fun shouldFindBooksById() {
        var book = Book(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        var expectedResponse = BookResponse(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        `when`(bookService.findById("1")).thenReturn(Mono.just(book))

        var actualResponse = webTestClient.get()
            .uri("/api/book/1")
            .exchange()
            .expectStatus().isOk
            .expectBody<BookResponse>()
            .returnResult()
            .responseBody

        bookResponseEquals(expectedResponse, actualResponse!!)
    }

    @Test
    fun shouldFindBooksByTitle() {
        var book = Book(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        var expectedResponse = BookResponse(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        `when`(bookService.findByTitle("title1")).thenReturn(Flux.just(book))

        var actualResponse = webTestClient.get()
            .uri("/api/book/title/title1")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<BookResponse>()
            .hasSize(1)
            .returnResult().responseBody?.get(0)

        bookResponseEquals(expectedResponse, actualResponse!!)
    }

    @Test
    fun shouldFindBooksByAuthor() {
        var book = Book(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        var expectedResponse = BookResponse(
            id = "1",
            title = "title1",
            authors = Arrays.asList("author1"),
            description = "description1",
            image = "image1",
            price = 100.0,
            quantity = 1
        )
        `when`(bookService.findByAuthor("author1")).thenReturn(Flux.just(book))

        var actualResponse = webTestClient.get()
            .uri("/api/book/author/author1")
            .exchange()
            .expectStatus().isOk
            .expectBodyList<BookResponse>()
            .hasSize(1)
            .returnResult().responseBody?.get(0)

        bookResponseEquals(expectedResponse, actualResponse!!)
    }


    @Test
    fun shouldThrowNotFoundExceptionWhenBookByIdDontExist() {
        `when`(bookService.findById("1")).thenReturn(Mono.empty())

        webTestClient.get()
            .uri("api/book/1")
            .exchange()
            .expectStatus().isNotFound
    }


    private fun bookResponseEquals(expected: BookResponse, actual: BookResponse) {
        Assertions.assertEquals(expected.id, actual.id);
        Assertions.assertEquals(expected.title, actual.title);
        Assertions.assertEquals(expected.authors, actual.authors);
        Assertions.assertEquals(expected.price, actual.price);
        Assertions.assertEquals(expected.quantity, actual.quantity);
        Assertions.assertEquals(expected.description, actual.description);
        Assertions.assertEquals(expected.image, actual.image);
    }
}