package com.goone.mangone.api.rest.request.comic;

import lombok.Data;

@Data
public class ComicSearchParams {
    private String name;
    private Short status;
}
