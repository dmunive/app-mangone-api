package com.goone.mangone.api.service;

import com.goone.mangone.api.entity.PageEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.page.PageRequest;
import com.goone.mangone.api.rest.request.page.PageSearchParams;
import org.springframework.data.domain.Pageable;

public interface PageService {
    PageEntity createPage(Long comicId, Long chapterId, PageRequest pageRequest);
    PageEntity readPage(Long comicId, Long chapterId, Long pageId);
    PageEntity updatePage(Long comicId, Long chapterId, Long pageId, PageRequest pageRequest);
    Paginator<PageEntity> searchPage(Long comicId, Long chapterId, PageSearchParams pageSearchParams, Pageable pageable);
    PageEntity deletePage(Long comicId, Long chapterId, Long pageId);
}
