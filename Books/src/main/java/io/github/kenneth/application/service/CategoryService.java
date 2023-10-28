package io.github.kenneth.application.service;

import io.github.kenneth.application.dto.CategoryRecord;
import io.github.kenneth.domain.Category;
import io.github.kenneth.framework.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Method responsible for find category
     *
     * @param id field relating to category identification
     */
    public CategoryRecord findById(final Integer id) {
        var category = categoryRepository.findById(id);
        return category.map(Category::toDTO).orElse(null);
    }

    /**
     * Method responsible for listing category
     */
    public List<CategoryRecord> findAll() {
        return categoryRepository.findAll().stream()
                .map(Category::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Method responsible for registering create a category
     *
     * @param categoryRecord field DTO for category
     */
    public CategoryRecord create(CategoryRecord categoryRecord) {
        var category = categoryRecord.toEntity();
        var newCategory = categoryRepository.save(category);
        return newCategory.toDTO();
    }


}