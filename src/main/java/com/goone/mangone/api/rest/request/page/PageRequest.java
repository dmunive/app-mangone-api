package com.goone.mangone.api.rest.request.page;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PageRequest {
    public interface PageCreateRequest {
    }
    public interface PageUpdateRequest {
    }
    @NotEmpty(groups = { PageCreateRequest.class })
    @NotNull(groups = { PageCreateRequest.class })
    private String image;
    @Digits(integer=10, fraction=0, groups = { PageUpdateRequest.class, PageUpdateRequest.class })
    @NotNull(groups = { PageCreateRequest.class, PageCreateRequest.class })
    private Integer number;
    @Digits(integer=1, fraction=0, groups = { PageUpdateRequest.class, PageUpdateRequest.class })
    @NotNull(groups = { PageUpdateRequest.class, PageUpdateRequest.class })
    private Short status;
}
