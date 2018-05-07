package com.goone.mangone.api.rest.request.comic;

import com.goone.mangone.api.rest.request.category.CategoryRequest;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ComicRequest {
    public interface ComicCreateRequest {
    }
    public interface ComicUpdateRequest {
    }
    @NotEmpty(groups = { ComicCreateRequest.class, ComicCreateRequest.class })
    @NotNull(groups = { ComicCreateRequest.class, ComicCreateRequest.class })
    private String name;
    @NotEmpty(groups = { ComicCreateRequest.class })
    @NotNull(groups = { ComicCreateRequest.class })
    private String image;
    @NotEmpty(groups = { ComicCreateRequest.class, ComicCreateRequest.class })
    @NotNull(groups = { ComicCreateRequest.class, ComicCreateRequest.class })
    private List<CategoryRequest> categories;
    @Digits(integer=1, fraction=0, groups = { ComicUpdateRequest.class, ComicUpdateRequest.class })
    @NotNull(groups = { ComicUpdateRequest.class, ComicUpdateRequest.class })
    private Short status;
}
