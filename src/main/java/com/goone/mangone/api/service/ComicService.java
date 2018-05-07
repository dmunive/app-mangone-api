package com.goone.mangone.api.service;

import com.goone.mangone.api.entity.ComicEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.comic.ComicRequest;
import com.goone.mangone.api.rest.request.comic.ComicSearchParams;
import org.springframework.data.domain.Pageable;

public interface ComicService {
    ComicEntity createComic(ComicRequest comicRequest);
    ComicEntity readComic(Long comicId);
    ComicEntity updateComic(Long comicId, ComicRequest comicRequest);
    Paginator<ComicEntity> searchComic(ComicSearchParams comicSearchParams, Pageable pageable);
    ComicEntity deleteComic(Long comicId);
}
