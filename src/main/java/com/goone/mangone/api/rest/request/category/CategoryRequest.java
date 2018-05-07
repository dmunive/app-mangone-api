package com.goone.mangone.api.rest.request.category;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryRequest {
    interface CategoryComicRequest {
    }
    public interface CategoryCreateRequest {
    }
    public interface CategoryUpdateRequest {
    }
    @Digits(integer=10, fraction=0, groups = { CategoryComicRequest.class })
    @NotNull(groups = { CategoryComicRequest.class })
    private Long categoryId;
    @NotEmpty(groups = { CategoryCreateRequest.class, CategoryUpdateRequest.class })
    @NotNull(groups = { CategoryCreateRequest.class, CategoryUpdateRequest.class })
    private String name;
    @Digits(integer=1, fraction=0, groups = { CategoryCreateRequest.class, CategoryUpdateRequest.class })
    @NotNull(groups = { CategoryCreateRequest.class, CategoryUpdateRequest.class })
    private Short status;
}
