package yevhen.bookstore.service.book;

import java.util.List;
import org.springframework.data.domain.Pageable;
import yevhen.bookstore.dto.book.BookDto;
import yevhen.bookstore.dto.book.BookDtoWithoutCategoryIds;
import yevhen.bookstore.dto.book.BookSearchParametersDto;
import yevhen.bookstore.dto.book.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long id);

    BookDto updateBookById(CreateBookRequestDto requestDto, Long id);

    void deleteBookById(Long id);

    List<BookDto> search(Pageable pageable, BookSearchParametersDto searchParameters);

    List<BookDtoWithoutCategoryIds> getBooksByCategory(Pageable pageable, Long id);
}
