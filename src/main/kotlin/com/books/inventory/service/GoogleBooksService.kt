package com.books.inventory.service

import com.books.inventory.config.GoogleBooksConfig
import com.books.inventory.dto.BookResponse
import com.books.inventory.dto.GoogleBookItem
import com.books.inventory.dto.GoogleBookList
import com.books.inventory.dto.GoogleBookResponse
import kotlinx.coroutines.reactive.collect
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono

@Component
class GoogleBooksService(private val client: WebClient, private val config: GoogleBooksConfig){

    fun getBooksByTitleAndAuthor(request: String): Flux<GoogleBookList> {
        val response = client.get()
            .uri{
                builder: UriBuilder ->
                builder.path("/books/v1/volumes")
                    .queryParam("q", request)
                    .queryParam("inauthor:", request )
                    .build()
            }
            .retrieve()
            .bodyToFlux(GoogleBookList::class.java)



        return response
    }


}