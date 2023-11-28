package yevhen.bookstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import yevhen.bookstore.dto.BookDto;
import yevhen.bookstore.dto.BookSearchParametersDto;
import yevhen.bookstore.dto.CreateBookRequestDto;
import yevhen.bookstore.exception.EntityNotFoundException;
import yevhen.bookstore.mapper.BookMapper;
import yevhen.bookstore.model.Book;
import yevhen.bookstore.repository.BookRepository;
import yevhen.bookstore.repository.specification.BookSpecificationBuilder;
import yevhen.bookstore.service.BookService;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

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

    @Override
    public List<BookDto> search(BookSearchParametersDto searchParameters) {
        Specification<Book> specification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(specification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
