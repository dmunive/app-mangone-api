package com.goone.mangone.api.service.impl;

import com.goone.mangone.api.dao.ChapterDao;
import com.goone.mangone.api.entity.ChapterEntity;
import com.goone.mangone.api.exception.NotExistEntityException;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.chapter.ChapterRequest;
import com.goone.mangone.api.rest.request.chapter.ChapterSearchParams;
import com.goone.mangone.api.service.ChapterService;
import com.goone.mangone.api.utils.FileUtils;
import com.goone.mangone.api.utils.ImageProperties;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    ImageProperties imageProperties;

    @Autowired
    ChapterDao chapterDAO;

    @Transactional
    public ChapterEntity createChapter(Long comicId, ChapterRequest chapterRequest) {
        ChapterEntity chapter = new ChapterEntity();
        chapter.setName(chapterRequest.getName());
        chapter.setImage(FileUtils.saveImage(chapterRequest.getImage(), imageProperties.getServer(), imageProperties.getChapters(), "image", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CHAPTERS_IMAGE_SAVE)));
        chapter.setNumber(chapterRequest.getNumber());
        chapter.setComicId(comicId);
        chapter.setCreatedAt(new Date());
        chapter.setStatus(chapterRequest.getStatus());
        chapter = chapterDAO.save(chapter);
        chapter.setImage(FileUtils.setPathImage(imageProperties.getDomain(), chapter.getImage()));
        return chapter;
    }

    public ChapterEntity readChapter(Long comicId, Long chapterId) {
        Optional<ChapterEntity> chapter = chapterDAO.findById(chapterId);
        if (!chapter.isPresent()) {
            throw new NotExistEntityException("chapterId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CHAPTERS_ID_NOT_EXIST));
        }
        chapter.get().setImage(FileUtils.setPathImage(imageProperties.getDomain(), chapter.get().getImage()));
        return chapter.get();
    }

    @Transactional
    public ChapterEntity updateChapter(Long comicId, Long chapterId, ChapterRequest chapterRequest) {
        Optional<ChapterEntity> chapterExist = chapterDAO.findById(chapterId);
        if (!chapterExist.isPresent()) {
            throw new NotExistEntityException("chapterId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CHAPTERS_ID_NOT_EXIST));
        }
        ChapterEntity chapter = chapterExist.get();
        chapter.setName(chapterRequest.getName());
        if (!StringUtils.isEmpty(chapterRequest.getImage())) {
            chapter.setImage(FileUtils.saveImage(chapterRequest.getImage(), imageProperties.getServer(), imageProperties.getChapters(), "image", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CHAPTERS_IMAGE_SAVE)));
        }
        chapter.setNumber(chapterRequest.getNumber());
        chapter.setUpdatedAt(new Date());
        chapter.setStatus(chapterRequest.getStatus());
        chapter = chapterDAO.save(chapter);
        chapter.setImage(FileUtils.setPathImage(imageProperties.getDomain(), chapter.getImage()));
        return chapter;
    }

    public Paginator<ChapterEntity> searchChapter(Long comicId, ChapterSearchParams params, Pageable pageable) {
        Page<ChapterEntity> pageChapterEntity = chapterDAO.searchChapter(comicId, params.getName(), params.getNumber(), params.getStatus(), pageable);
        for(ChapterEntity chapter: pageChapterEntity.getContent()){
            chapter.setImage(FileUtils.setPathImage(imageProperties.getDomain(), chapter.getImage()));
        }
        return new Paginator<>(pageChapterEntity);
    }

    @Transactional
    public ChapterEntity deleteChapter(Long comicId, Long chapterId) {
        Optional<ChapterEntity> chapterExist = chapterDAO.findById(chapterId);
        if (!chapterExist.isPresent()) {
            throw new NotExistEntityException("chapterId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CHAPTERS_ID_NOT_EXIST));
        }
        ChapterEntity chapter = chapterExist.get();
        chapter.setDeletedAt(new Date());
        chapter = chapterDAO.save(chapter);
        chapter.setImage(FileUtils.setPathImage(imageProperties.getDomain(), chapter.getImage()));
        return chapter;
    }

}
