package com.goone.mangone.api.rest.controller.admin;

import com.goone.mangone.api.entity.ChapterEntity;
import com.goone.mangone.api.model.Paginator;
import com.goone.mangone.api.rest.request.chapter.ChapterRequest;
import com.goone.mangone.api.rest.request.chapter.ChapterSearchParams;
import com.goone.mangone.api.rest.response.GenericResponse;
import com.goone.mangone.api.rest.response.StatusResponse;
import com.goone.mangone.api.service.ChapterService;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChapterController extends AdminController{

    @Autowired
    ManageMessageApplication manageMessageApplication;
    
    @Autowired
    ChapterService chapterService;

    @PostMapping(path = "/comics/{comicId}/chapters")
    public ResponseEntity<?> createChapter(@PathVariable Long comicId, @Validated({ ChapterRequest.ChapterCreateRequest.class }) @RequestBody ChapterRequest chapterRequest) {
        ChapterEntity chapter = chapterService.createChapter(comicId, chapterRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CHAPTERS_CREATE), chapter);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/comics/{comicId}/chapters/{chapterId}")
    public ResponseEntity<?> readChapter(@PathVariable Long comicId, @PathVariable Long chapterId) {
        ChapterEntity chapter = chapterService.readChapter(comicId, chapterId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CHAPTERS_READ), chapter);
        return ResponseEntity.ok().body(genericResponse);
    }

    @PutMapping(path = "/comics/{comicId}/chapters/{chapterId}")
    public ResponseEntity<?> updateChapter(@PathVariable Long comicId, @PathVariable Long chapterId, @Validated({ ChapterRequest.ChapterUpdateRequest.class }) @RequestBody ChapterRequest chapterRequest) throws Exception{
        ChapterEntity chapter = chapterService.updateChapter(comicId, chapterId,chapterRequest);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CHAPTERS_UPDATE), chapter);
        return ResponseEntity.ok().body(genericResponse);
    }

    @DeleteMapping(path = "/comics/{comicId}/chapters/{chapterId}")
    public ResponseEntity<?>  deleteChapter(@PathVariable Long comicId, @PathVariable Long chapterId) {
        ChapterEntity chapter = chapterService.deleteChapter(comicId, chapterId);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CHAPTERS_DELETE), chapter);
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(path = "/comics/{comicId}/chapters")
    public ResponseEntity<?> searchChapter(@PathVariable Long comicId, @ModelAttribute ChapterSearchParams chapterSearchParams, Pageable pageable){
        Paginator<ChapterEntity> chapters = chapterService.searchChapter(comicId, chapterSearchParams, pageable);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, manageMessageApplication.getMessage(ManageMessageApplication.MESSAGE_CHAPTERS_SEARCH), chapters);
        return ResponseEntity.ok().body(genericResponse);
    }

}
