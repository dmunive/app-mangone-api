package com.goone.mangone.api.service;

import com.goone.mangone.api.entity.ChapterEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.chapter.ChapterRequest;
import com.goone.mangone.api.rest.request.chapter.ChapterSearchParams;
import org.springframework.data.domain.Pageable;

public interface ChapterService {
    ChapterEntity createChapter(Long comicId, ChapterRequest chapterRequest);
    ChapterEntity readChapter(Long comicId, Long chapterId);
    ChapterEntity updateChapter(Long comicId, Long chapterId, ChapterRequest chapterRequest);
    Paginator<ChapterEntity> searchChapter(Long comicId, ChapterSearchParams chapterSearchParams, Pageable pageable);
    ChapterEntity deleteChapter(Long comicId, Long chapterId);
}
