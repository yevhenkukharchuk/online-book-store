package yevhen.bookstore.mapper;

import org.mapstruct.Mapper;
import yevhen.bookstore.config.MapperConfig;
import yevhen.bookstore.dto.BookDto;
import yevhen.bookstore.dto.CreateBookRequestDto;
import yevhen.bookstore.model.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
