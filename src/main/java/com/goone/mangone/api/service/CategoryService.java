package com.goone.mangone.api.service;

import com.goone.mangone.api.entity.CategoryEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.category.CategoryRequest;
import com.goone.mangone.api.rest.request.category.CategorySearchParams;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryEntity createCategory(CategoryRequest categoryRequest);
    CategoryEntity readCategory(Long categoryId);
    CategoryEntity updateCategory(Long categoryId, CategoryRequest categoryRequest);
    Paginator<CategoryEntity> searchCategory(CategorySearchParams categorySearchParams, Pageable pageable);
    CategoryEntity deleteCategory(Long categoryId);
}
