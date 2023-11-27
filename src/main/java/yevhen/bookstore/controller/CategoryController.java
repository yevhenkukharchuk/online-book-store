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
import yevhen.bookstore.dto.book.BookDtoWithoutCategoryIds;
import yevhen.bookstore.dto.category.CategoryDto;
import yevhen.bookstore.dto.category.CreateCategoryRequestDto;
import yevhen.bookstore.service.book.BookService;
import yevhen.bookstore.service.category.CategoryService;

@Tag(name = "Book categories managing", description = "Endpoints for managing book categories")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book category", description = "Create a new book category")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @GetMapping
    @Operation(summary = "Get all book categories",
            description = "Get a list of all available book categories")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one book category by id", description = "Get one book category by id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book category by id",
            description = "Update a book category with certain id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryDto updateCategoryById(@RequestBody @Valid CreateCategoryRequestDto requestDto,
                                  @PathVariable Long id) {
        return categoryService.updateCategoryById(requestDto, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete one book category by id",
            description = "Delete one book category by id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategoryById(Authentication authentication, @PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

    @GetMapping("/{id}/books")
    @Operation(summary = "Get all books by category",
            description = "Retrieve all books by a specific category")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public List<BookDtoWithoutCategoryIds> getBooksByCategory(Pageable pageable,
                                                              @PathVariable Long id) {
        return bookService.getBooksByCategory(pageable, id);
    }
}
