package yevhen.bookstore.service.category;

import java.util.List;
import org.springframework.data.domain.Pageable;
import yevhen.bookstore.dto.category.CategoryDto;
import yevhen.bookstore.dto.category.CreateCategoryRequestDto;

public interface CategoryService {
    CategoryDto save(CreateCategoryRequestDto requestDto);

    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getCategoryById(Long id);

    CategoryDto updateCategoryById(CreateCategoryRequestDto requestDto, Long id);

    void deleteCategoryById(Long id);
}
