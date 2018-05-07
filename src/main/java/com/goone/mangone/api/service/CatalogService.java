package com.goone.mangone.api.service;

import com.goone.mangone.api.entity.CatalogEntity;

public interface CatalogService {
    CatalogEntity readCatalogByCode(String code);
}
