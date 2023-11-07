package yevhen.bookstore.service;

import java.util.List;
import yevhen.bookstore.dto.BookDto;
import yevhen.bookstore.dto.BookSearchParametersDto;
import yevhen.bookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto getBookById(Long id);

    BookDto updateBookById(CreateBookRequestDto requestDto, Long id);

    void deleteBookById(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters);
}
