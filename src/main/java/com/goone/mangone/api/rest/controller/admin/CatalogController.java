package com.goone.mangone.api.rest.controller.admin;

import com.goone.mangone.api.dao.CatalogDao;
import com.goone.mangone.api.entity.CatalogEntity;
import com.goone.mangone.api.rest.response.GenericResponse;
import com.goone.mangone.api.rest.response.StatusResponse;
import com.goone.mangone.api.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CatalogController extends AdminController{

    @Autowired
    CatalogService catalogService;
    @Autowired
    CatalogDao catalogDAO;

    @GetMapping(path = "/catalogs")
    public ResponseEntity<?> readCatalogByCode(@Param(value = "code") String code){
        CatalogEntity catalogs = catalogService.readCatalogByCode(code);
        GenericResponse genericResponse = new GenericResponse(StatusResponse.SUCCESS, catalogs);
        return ResponseEntity.ok().body(genericResponse);
    }

}
