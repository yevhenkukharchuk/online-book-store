package yevhen.bookstore.repository.specification.provider;

import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import yevhen.bookstore.model.Book;
import yevhen.bookstore.repository.specification.SpecificationProvider;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "author";
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder)
                -> root.get("author").in(Arrays.stream(params).toArray());
    }
}
