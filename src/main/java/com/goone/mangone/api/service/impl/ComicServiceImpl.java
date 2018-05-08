package com.goone.mangone.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goone.mangone.api.dao.CategoryComicDao;
import com.goone.mangone.api.dao.ComicDao;
import com.goone.mangone.api.entity.CategoryEntity;
import com.goone.mangone.api.entity.ComicEntity;
import com.goone.mangone.api.exception.NotExistEntityException;
import com.goone.mangone.api.exception.UniqueFieldException;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.category.CategoryRequest;
import com.goone.mangone.api.rest.request.comic.ComicRequest;
import com.goone.mangone.api.rest.request.comic.ComicSearchParams;
import com.goone.mangone.api.service.ComicService;
import com.goone.mangone.api.utils.FileUtils;
import com.goone.mangone.api.utils.ImageProperties;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    ImageProperties imageProperties;

    @Autowired
    ComicDao comicDAO;

    @Autowired
    CategoryComicDao categoryComicDao;

    ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public ComicEntity createComic(ComicRequest comicRequest) {
        Optional<ComicEntity> comicExist = comicDAO.findByName(comicRequest.getName());
        if (comicExist.isPresent()) {
            throw new UniqueFieldException("name", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_COMICS_NAME_UNIQUE));
        }
        ComicEntity comic = new ComicEntity();
        comic.setName(comicRequest.getName());
        comic.setImage(FileUtils.saveImage(comicRequest.getImage(), imageProperties.getServer(), imageProperties.getComics(), "image", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_COMICS_IMAGE_SAVE)));
        comic.setCreatedAt(new Date());
        comic.setCategories(formatCategories(comicRequest));
        comic.setStatus(comicRequest.getStatus());
        comic = comicDAO.save(comic);
        comicDAO.flush();
        comic.setImage(FileUtils.setPathImage(imageProperties.getDomain(), comic.getImage()));
        return comic;
    }

    public ComicEntity readComic(Long comicId) {
        Optional<ComicEntity> comic = comicDAO.findById(comicId);
        if (!comic.isPresent()) {
            throw new NotExistEntityException("comicId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_COMICS_ID_NOT_EXIST));
        }
        comic.get().setImage(FileUtils.setPathImage(imageProperties.getDomain(), comic.get().getImage()));
        return comic.get();
    }

    @Transactional
    public ComicEntity updateComic(Long comicId, ComicRequest comicRequest) {
        Optional<ComicEntity> comicExist = comicDAO.findById(comicId);
        if (!comicExist.isPresent()) {
            throw new NotExistEntityException("comicId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_COMICS_ID_NOT_EXIST));
        }
        Optional<ComicEntity> comicNameExist = comicDAO.findByComicIdNotAndName(comicId, comicRequest.getName());
        if (comicNameExist.isPresent()) {
            throw new UniqueFieldException("name", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_COMICS_NAME_UNIQUE));
        }
        ComicEntity comic = comicExist.get();
        comic.setName(comicRequest.getName());
        if (!StringUtils.isEmpty(comicRequest.getImage())) {
            comic.setImage(FileUtils.saveImage(comicRequest.getImage(), imageProperties.getServer(), imageProperties.getComics(), "image", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_COMICS_IMAGE_SAVE)));
        }
        comic.setCategories(formatCategories(comicRequest));
        comic.setUpdatedAt(new Date());
        comic.setStatus(comicRequest.getStatus());
        comic = comicDAO.save(comic);
        comicDAO.flush();
        comic.setImage(FileUtils.setPathImage(imageProperties.getDomain(), comic.getImage()));
        return comic;
    }

    public Paginator<ComicEntity> searchComic(ComicSearchParams params, Pageable pageable) {
        Page<ComicEntity> pageComicEntity = comicDAO.searchComic(params.getName(), params.getStatus(), pageable);
        for(ComicEntity comic: pageComicEntity.getContent()){
            comic.setImage(FileUtils.setPathImage(imageProperties.getDomain(), comic.getImage()));
        }
        return new Paginator<>(pageComicEntity);
    }

    @Transactional
    public ComicEntity deleteComic(Long comicId) {
        Optional<ComicEntity> comicFinded = comicDAO.findById(comicId);
        if (!comicFinded.isPresent()) {
            throw new NotExistEntityException("comicId", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_COMICS_ID_NOT_EXIST));
        }
        ComicEntity comic = comicFinded.get();
        comic.setDeletedAt(new Date());
        comic = comicDAO.save(comic);
        comic.setImage(FileUtils.setPathImage(imageProperties.getDomain(), comic.getImage()));
        return comic;
    }

    private List<CategoryEntity> formatCategories(ComicRequest comicRequest) {
        List<CategoryEntity> categories = new ArrayList<>();
        for (CategoryRequest categoryRequest : comicRequest.getCategories()) {
            CategoryEntity category = modelMapper.map(categoryRequest, CategoryEntity.class);
            categories.add(category);
        }
        return categories;
    }

}
