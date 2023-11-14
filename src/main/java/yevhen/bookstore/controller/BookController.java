package yevhen.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import yevhen.bookstore.dto.BookDto;
import yevhen.bookstore.dto.BookSearchParametersDto;
import yevhen.bookstore.dto.CreateBookRequestDto;
import yevhen.bookstore.service.BookService;

@Tag(name = "Online book store managing", description = "Endpoints for managing book store")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get all books", description = "Get a list of all available books")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one book by id", description = "Get one book by id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book", description = "Create a new book")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book by id", description = "Update a book with certain id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public BookDto updateBookById(@RequestBody @Valid CreateBookRequestDto requestDto,
                                  @PathVariable Long id) {
        return bookService.updateBookById(requestDto, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete one book by id", description = "Delete one book by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteBookById(Authentication authentication, @PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Filter books by parameters",
            description = "Filter books by titles and authors")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<BookDto> searchBooks(Pageable pageable, BookSearchParametersDto searchParameters) {
        return bookService.search(pageable, searchParameters);
    }
}
