package io.micronaut.multitenancy.gorm.httpheader

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.Requires
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.Secured
import io.micronaut.security.rules.SecurityRule
import io.micronaut.security.utils.SecurityService

@Requires(property = 'spec.name', value = 'multitenancy.httpheader.gorm')
@CompileStatic
@Controller("/api")
class BooksController {

    private final BookService bookService

    BooksController(BookService bookService) {
        this.bookService = bookService
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @Get("/books")
    List<String> books() {
        List<Book> books = bookService.list()
        books*.title
    }
}
