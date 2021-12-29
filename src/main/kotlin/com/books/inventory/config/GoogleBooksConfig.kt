package com.books.inventory.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class GoogleBooksConfig(@Value("https://www.googleapis.com") val baseUrl: String){

    @Bean
    fun createWebClient(): WebClient{
        return WebClient.create(baseUrl)
    }

}