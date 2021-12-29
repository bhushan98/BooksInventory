package com.books.inventory.integration

import org.springframework.stereotype.Service
import org.testcontainers.containers.DockerComposeContainer
import java.io.File
import javax.annotation.PostConstruct

@Service
class MongoContainerInitializer :
    DockerComposeContainer<MongoContainerInitializer>(File("src/test/resources/docker-compose-test.yml")) {

    @PostConstruct
    fun init() {
        this.start()
    }

}