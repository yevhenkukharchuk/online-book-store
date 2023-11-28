package yevhen.bookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yevhen.bookstore.dto.BookDto;
import yevhen.bookstore.dto.CreateBookRequestDto;
import yevhen.bookstore.exception.EntityNotFoundException;
import yevhen.bookstore.mapper.BookMapper;
import yevhen.bookstore.model.Book;
import yevhen.bookstore.repository.BookRepository;
import yevhen.bookstore.service.BookService;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't get book by id:" + id));
    }

    @Override
    public BookDto updateBookById(CreateBookRequestDto requestDto, Long id) {
        Book bookFromDB = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id:" + id));
        bookMapper.updateBook(requestDto, bookFromDB);
        return bookMapper.toDto(bookRepository.save(bookFromDB));
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id:" + id));
        bookRepository.deleteById(id);
    }
}
