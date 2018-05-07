package com.goone.mangone.api.rest.controller.admin;

import com.goone.mangone.api.entity.PageEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.page.PageRequest;
import com.goone.mangone.api.rest.request.page.PageSearchParams;
import com.goone.mangone.api.rest.response.GenericResponse;
import com.goone.mangone.api.rest.response.StatusResponse;
import com.goone.mangone.api.service.PageService;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class PageController extends AdminController{

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    PageService pageService;

    @PostMapping(path = "/comics/{comicId}/chapters/{chapterId}/pages")
    public ResponseEntity<?> createPage(@PathVariable Long comicId, @PathVariable Long chapterId, @Validated({ PageRequest.PageCreateRequest.class }) @RequestBody PageRequest pageRequest) {
        PageEntity page = pageService.createPage(comicId, chapterId, pageRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_PAGES_CREATE), page);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/comics/{comicId}/chapters/{chapterId}/pages/{pageId}")
    public ResponseEntity<?> readPage(@PathVariable Long comicId, @PathVariable Long chapterId, @PathVariable Long pageId) {
        PageEntity page = pageService.readPage(comicId, chapterId, pageId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_PAGES_READ), page);
        return ResponseEntity.ok().body(genericResponse);
    }

    @PutMapping(path = "/comics/{comicId}/chapters/{chapterId}/pages/{pageId}")
    public ResponseEntity<?> updatePage(@PathVariable Long comicId, @PathVariable Long chapterId, @PathVariable Long pageId, @Validated({ PageRequest.PageUpdateRequest.class }) @RequestBody PageRequest pageRequest) {
        PageEntity page = pageService.updatePage(comicId, chapterId, pageId,pageRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_PAGES_UPDATE), page);
        return ResponseEntity.ok().body(genericResponse);
    }

    @DeleteMapping(path = "/comics/{comicId}/chapters/{chapterId}/pages/{pageId}")
    public ResponseEntity<?>  deletePage(@PathVariable Long comicId, @PathVariable Long chapterId, @PathVariable Long pageId) {
        PageEntity page = pageService.deletePage(comicId, chapterId, pageId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_PAGES_DELETE), page);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/comics/{comicId}/chapters/{chapterId}/pages")
    public ResponseEntity<?> searchPage(@PathVariable Long comicId, @PathVariable Long chapterId, @ModelAttribute PageSearchParams pageSearchParams, Pageable pageable){
        Paginator<PageEntity> pages = pageService.searchPage(comicId, chapterId, pageSearchParams, pageable);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_PAGES_SEARCH), pages);
        return ResponseEntity.ok().body(genericResponse);
    }

}
