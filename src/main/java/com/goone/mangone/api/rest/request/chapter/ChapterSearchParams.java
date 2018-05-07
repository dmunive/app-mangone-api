package com.goone.mangone.api.rest.request.chapter;

import lombok.Data;

@Data
public class ChapterSearchParams {
    private String name;
    private Integer number;
    private Short status;
}
