package com.books.inventory.repositories

import com.books.inventory.models.Book
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface BookRepository : ReactiveMongoRepository<Book, String>