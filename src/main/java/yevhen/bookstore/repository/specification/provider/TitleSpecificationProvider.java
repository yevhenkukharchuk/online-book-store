package yevhen.bookstore.repository.specification.provider;

import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import yevhen.bookstore.model.Book;
import yevhen.bookstore.repository.specification.SpecificationProvider;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "title";
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder)
                -> root.get("title").in(Arrays.stream(params).toArray());
    }
}
