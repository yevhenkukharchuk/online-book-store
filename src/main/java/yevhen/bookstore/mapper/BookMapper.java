package yevhen.bookstore.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import yevhen.bookstore.config.MapperConfig;
import yevhen.bookstore.dto.BookDto;
import yevhen.bookstore.dto.CreateBookRequestDto;
import yevhen.bookstore.model.Book;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBook(CreateBookRequestDto requestDto, @MappingTarget Book book);
}
