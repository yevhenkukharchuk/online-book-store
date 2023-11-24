package yevhen.bookstore.service.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import yevhen.bookstore.dto.category.CategoryDto;
import yevhen.bookstore.dto.category.CreateCategoryRequestDto;
import yevhen.bookstore.exception.EntityNotFoundException;
import yevhen.bookstore.mapper.CategoryMapper;
import yevhen.bookstore.model.Category;
import yevhen.bookstore.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toModel(requestDto)));
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Can't get category by id:" + id));
    }

    @Override
    public CategoryDto updateCategoryById(CreateCategoryRequestDto requestDto, Long id) {
        Category categoryFromDB = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't get category by id:" + id));
        categoryMapper.updateCategory(requestDto, categoryFromDB);
        return categoryMapper.toDto(categoryRepository.save(categoryFromDB));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can't get category by id:" + id));
        categoryRepository.deleteById(id);
    }
}
