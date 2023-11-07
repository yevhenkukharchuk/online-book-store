package yevhen.bookstore.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import yevhen.bookstore.dto.BookSearchParametersDto;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParametersDto searchParameters);
}
