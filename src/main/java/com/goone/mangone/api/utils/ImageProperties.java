package com.goone.mangone.api.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ImageProperties {

    @Value("${image.path.server}")
    private String server;
    @Value("${image.path.domain}")
    private String domain;
    @Value("${image.path.comics}")
    private String comics;
    @Value("${image.path.chapters}")
    private String chapters;
    @Value("${image.path.pages}")
    private String pages;

}
