package com.goone.mangone.api.rest.controller.admin;

import com.goone.mangone.api.entity.ComicEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.comic.ComicRequest;
import com.goone.mangone.api.rest.request.comic.ComicSearchParams;
import com.goone.mangone.api.rest.response.GenericResponse;
import com.goone.mangone.api.rest.response.StatusResponse;
import com.goone.mangone.api.service.ComicService;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComicController extends AdminController{

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    ComicService comicService;

    @PostMapping(path = "/comics")
    public ResponseEntity<?> createComic(@Validated({ ComicRequest.ComicCreateRequest.class }) @RequestBody ComicRequest comicRequest){
        ComicEntity comic = comicService.createComic(comicRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_COMICS_CREATE), comic);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/comics/{comicId}")
    public ResponseEntity<?> readComic(@PathVariable Long comicId){
        ComicEntity comic = comicService.readComic(comicId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_COMICS_READ), comic);
        return ResponseEntity.ok().body(genericResponse);
    }

    @PutMapping(path = "/comics/{comicId}")
    public ResponseEntity<?> updateComic(@PathVariable Long comicId, @Validated({ ComicRequest.ComicUpdateRequest.class }) @RequestBody ComicRequest comicRequest){
        ComicEntity comic = comicService.updateComic(comicId,comicRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_COMICS_UPDATE), comic);
        return ResponseEntity.ok().body(genericResponse);
    }

    @DeleteMapping(path = "/comics/{comicId}")
    public ResponseEntity<?>  deleteComic(@PathVariable Long comicId){
        ComicEntity comic = comicService.deleteComic(comicId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_COMICS_DELETE), comic);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/comics")
    public ResponseEntity<?> searchComic(@ModelAttribute ComicSearchParams comicSearchParams, Pageable pageable){
        Paginator<ComicEntity> comics = comicService.searchComic(comicSearchParams, pageable);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_COMICS_SEARCH), comics);
        return ResponseEntity.ok().body(genericResponse);
    }

}
