package yevhen.bookstore.service;

import java.util.List;
import yevhen.bookstore.dto.BookDto;
import yevhen.bookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll();

    BookDto getBookById(Long id);
}
