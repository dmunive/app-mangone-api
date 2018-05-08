package com.goone.mangone.api.service.impl;

import com.goone.mangone.api.dao.PageDao;
import com.goone.mangone.api.entity.PageEntity;
import com.goone.mangone.api.exception.NotExistEntityException;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.page.PageRequest;
import com.goone.mangone.api.rest.request.page.PageSearchParams;
import com.goone.mangone.api.service.PageService;
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
public class PageServiceImpl implements PageService {

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    ImageProperties imageProperties;

    @Autowired
    PageDao pageDAO;

    @Transactional
    public PageEntity createPage(Long comicId, Long chapterId, PageRequest pageRequest) {
        PageEntity page = new PageEntity();
        page.setNumber(pageRequest.getNumber());
        page.setChapterId(chapterId);
        page.setImage(FileUtils.saveImage(pageRequest.getImage(), imageProperties.getServer(), imageProperties.getComics(), "image", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_PAGES_IMAGE_SAVE)));
        page.setCreatedAt(new Date());
        page.setStatus(pageRequest.getStatus());
        return pageDAO.save(page);
    }

    public PageEntity readPage(Long comicId, Long chapterId, Long pageId) {
        Optional<PageEntity> page = pageDAO.findById(pageId);
        if(!page.isPresent()){
            throw new NotExistEntityException("pageId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_PAGES_ID_NOT_EXIST));
        }
        page.get().setImage(FileUtils.setPathImage(imageProperties.getDomain(), page.get().getImage()));
        return page.get();
    }

    @Transactional
    public PageEntity updatePage(Long comicId, Long chapterId, Long pageId, PageRequest pageRequest) {
        Optional<PageEntity> pageExist = pageDAO.findById(pageId);
        if(!pageExist.isPresent()){
            throw new NotExistEntityException("pageId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_PAGES_ID_NOT_EXIST));
        }
        PageEntity page = pageExist.get();
        page.setNumber(pageRequest.getNumber());
        if(!StringUtils.isEmpty(pageRequest.getImage())){
            page.setImage(FileUtils.saveImage(pageRequest.getImage(), imageProperties.getServer(), imageProperties.getComics(), "image", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_PAGES_IMAGE_SAVE)));
        }
        page.setUpdatedAt(new Date());
        page.setStatus(pageRequest.getStatus());
        return pageDAO.save(page);
    }

    public Paginator<PageEntity> searchPage(Long comicId, Long chapterId, PageSearchParams params, Pageable pageable){
        Page<PageEntity> pagePageEntity= pageDAO.searchPage(chapterId, params.getNumber(), params.getStatus(), pageable);
        for(PageEntity page: pagePageEntity.getContent()){
            page.setImage(FileUtils.setPathImage(imageProperties.getDomain(), page.getImage()));
        }
        return new Paginator<>(pagePageEntity);
    }

    @Transactional
    public PageEntity deletePage(Long comicId, Long chapterId, Long pageId) {
        Optional<PageEntity> pageExist = pageDAO.findById(pageId);
        if(!pageExist.isPresent()){
            throw new NotExistEntityException("pageId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_PAGES_ID_NOT_EXIST));
        }
        PageEntity page = pageExist.get();
        page.setDeletedAt(new Date());
        page = pageDAO.save(page);
        page.setImage(FileUtils.setPathImage(imageProperties.getDomain(), page.getImage()));
        return page;
    }

}
