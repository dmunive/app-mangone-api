package com.goone.mangone.api.rest.request.category;

import lombok.Data;

@Data
public class CategorySearchParams {
    private String name;
    private Short status;
}
