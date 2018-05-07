package com.goone.mangone.api.model;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class Paginator<T> {

    private List<T> content;

    private Boolean last;

    private Integer totalPages;

    private Long totalElements;

    private Integer numberOfElements;

    private Boolean first;

    private Integer size;

    private Integer number;

    public Paginator(Page<T> page) {
        this.content = page.getContent();
        this.last = page.isLast();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.numberOfElements = page.getNumberOfElements();
        this.first = page.isFirst();
        this.size = page.getSize();
        this.number = page.getNumber();
    }
}
