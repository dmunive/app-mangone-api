package com.goone.mangone.api.service.impl;

import com.goone.mangone.api.dao.CatalogDao;
import com.goone.mangone.api.exception.NotExistEntityException;
import com.goone.mangone.api.service.CatalogService;
import com.goone.mangone.api.entity.CatalogEntity;
import com.goone.mangone.api.utils.ManageMessageApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    ManageMessageApplication manageMessageApplication;

    @Autowired
    CatalogDao catalogDao;

    public CatalogEntity readCatalogByCode(String code){
        Optional<CatalogEntity> catalog = catalogDao.findByCode(code);
        if(!catalog.isPresent()){
            throw new NotExistEntityException("code", manageMessageApplication.getMessage(ManageMessageApplication.ERROR_CATALOGS_CODE_NOT_EXIST));
        }
        return catalog.get();
    }
}
