package com.goone.mangone.api.rest.controller.admin;

import com.goone.mangone.api.entity.CategoryEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.category.CategoryRequest;
import com.goone.mangone.api.rest.request.category.CategorySearchParams;
import com.goone.mangone.api.rest.response.GenericResponse;
import com.goone.mangone.api.rest.response.StatusResponse;
import com.goone.mangone.api.service.CategoryService;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController extends AdminController{

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    CategoryService categoryService;

    @PostMapping(path = "/categories")
    public ResponseEntity<?> createCategory(@Validated({ CategoryRequest.CategoryCreateRequest.class }) @RequestBody CategoryRequest categoryRequest) {
        CategoryEntity category = categoryService.createCategory(categoryRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CATEGORIES_CREATE), category);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/categories/{categoryId}")
    public ResponseEntity<?> readCategory(@PathVariable Long categoryId) {
        CategoryEntity category = categoryService.readCategory(categoryId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CATEGORIES_READ), category);
        return ResponseEntity.ok().body(genericResponse);
    }

    @PutMapping(path = "/categories/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId, @Validated({ CategoryRequest.CategoryUpdateRequest.class }) @RequestBody CategoryRequest categoryRequest) {
        CategoryEntity category = categoryService.updateCategory(categoryId,categoryRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CATEGORIES_UPDATE), category);
        return ResponseEntity.ok().body(genericResponse);
    }

    @DeleteMapping(path = "/categories/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        CategoryEntity category = categoryService.deleteCategory(categoryId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CATEGORIES_DELETE), category);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/categories")
    public ResponseEntity<?> searchCategory(@ModelAttribute CategorySearchParams categorySearchParams, Pageable pageable){
        Paginator<CategoryEntity> categorys = categoryService.searchCategory(categorySearchParams, pageable);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CATEGORIES_SEARCH), categorys);
        return ResponseEntity.ok().body(genericResponse);
    }

}
