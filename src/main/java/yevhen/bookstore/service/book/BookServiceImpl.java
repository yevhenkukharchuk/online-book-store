package yevhen.bookstore.service.book;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import yevhen.bookstore.dto.book.BookDto;
import yevhen.bookstore.dto.book.BookDtoWithoutCategoryIds;
import yevhen.bookstore.dto.book.BookSearchParametersDto;
import yevhen.bookstore.dto.book.CreateBookRequestDto;
import yevhen.bookstore.exception.EntityNotFoundException;
import yevhen.bookstore.mapper.BookMapper;
import yevhen.bookstore.model.Book;
import yevhen.bookstore.model.Category;
import yevhen.bookstore.repository.BookRepository;
import yevhen.bookstore.repository.CategoryRepository;
import yevhen.bookstore.repository.specification.BookSpecificationBuilder;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;
    private final CategoryRepository categoryRepository;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toBook(requestDto);
        setCategories(book, requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
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
        setCategories(bookFromDB, requestDto);
        return bookMapper.toDto(bookRepository.save(bookFromDB));
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't find book by id:" + id));
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(Pageable pageable, BookSearchParametersDto searchParameters) {
        Specification<Book> specification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(specification)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getBooksByCategory(Pageable pageable, Long id) {
        return bookRepository.findAllByCategoriesId(id).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }

    private void setCategories(Book book, CreateBookRequestDto requestDto) {
        Set<Category> categories = requestDto.categoryIds().stream()
                .map(Category::new)
                .collect(Collectors.toSet());
        book.setCategories(categories);
    }
}
