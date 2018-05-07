package com.goone.mangone.api.service.impl;

import com.goone.mangone.api.dao.CategoryDao;
import com.goone.mangone.api.entity.CategoryEntity;
import com.goone.mangone.api.exception.NotExistEntityException;
import com.goone.mangone.api.exception.UniqueFieldException;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.category.CategoryRequest;
import com.goone.mangone.api.rest.request.category.CategorySearchParams;
import com.goone.mangone.api.service.CategoryService;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    CategoryDao categoryDAO;

    @Transactional
    public CategoryEntity createCategory(CategoryRequest categoryRequest) {
        Optional<CategoryEntity> categoryExist = categoryDAO.findByName(categoryRequest.getName());
        if (categoryExist.isPresent()) {
            throw new UniqueFieldException("name", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CATEGORIES_NAME_UNIQUE));
        }
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryRequest.getName());
        category.setCreatedAt(new Date());
        category.setStatus(categoryRequest.getStatus());
        category = categoryDAO.save(category);
        return category;
    }

    public CategoryEntity readCategory(Long categoryId) {
        Optional<CategoryEntity> category = categoryDAO.findById(categoryId);
        if(!category.isPresent()) {
            throw new NotExistEntityException("categoryId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CATEGORIES_ID_NOT_EXIST));
        }
        return category.get();
    }

    @Transactional
    public CategoryEntity updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Optional<CategoryEntity> categoryExist = categoryDAO.findById(categoryId);
        if(!categoryExist.isPresent()) {
            throw new NotExistEntityException("categoryId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CATEGORIES_ID_NOT_EXIST));
        }
        Optional<CategoryEntity> categoryNameExist = categoryDAO.findByCategoryIdNotAndName(categoryId, categoryRequest.getName());
        if(categoryNameExist.isPresent()) {
            throw new UniqueFieldException("name", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CATEGORIES_NAME_UNIQUE));
        }
        CategoryEntity category = categoryExist.get();
        category.setName(categoryRequest.getName());
        category.setUpdatedAt(new Date());
        category.setStatus(categoryRequest.getStatus());
        category = categoryDAO.save(category);
        return category;
    }

    public Paginator<CategoryEntity> searchCategory(CategorySearchParams params, Pageable pageable){
        Page<CategoryEntity> pageCategoryEntity= categoryDAO.searchCategory(params.getName(), params.getStatus(), pageable);
        return new Paginator<>(pageCategoryEntity);
    }

    @Transactional
    public CategoryEntity deleteCategory(Long categoryId) {
        Optional<CategoryEntity> categoryExist = categoryDAO.findById(categoryId);
        if(!categoryExist.isPresent()){
            throw new NotExistEntityException("categoryId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CATEGORIES_ID_NOT_EXIST));
        }
        CategoryEntity category = categoryExist.get();
        category.setDeletedAt(new Date());
        category = categoryDAO.save(category);
        return category;
    }

}
