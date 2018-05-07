package com.goone.mangone.api.rest.request.chapter;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChapterRequest {
    public interface ChapterCreateRequest {
    }
    public interface ChapterUpdateRequest {
    }
    @NotEmpty(groups = { ChapterCreateRequest.class, ChapterCreateRequest.class })
    @NotNull(groups = { ChapterCreateRequest.class, ChapterCreateRequest.class })
    private String name;
    @NotEmpty(groups = { ChapterCreateRequest.class })
    @NotNull(groups = { ChapterCreateRequest.class })
    private String image;
    @Digits(integer=10, fraction=0, groups = { ChapterCreateRequest.class, ChapterCreateRequest.class })
    @NotNull(groups = { ChapterCreateRequest.class, ChapterCreateRequest.class })
    private Integer number;
    @Digits(integer=1, fraction=0, groups = { ChapterCreateRequest.class, ChapterCreateRequest.class })
    @NotNull(groups = { ChapterUpdateRequest.class, ChapterUpdateRequest.class })
    private Short status;
}
