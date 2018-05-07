package com.goone.mangone.api.dao;

import com.goone.mangone.api.entity.CatalogEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogDao extends JpaRepository<CatalogEntity,Long> {
    @EntityGraph(value = "Catalog.CatalogDetail", type = EntityGraph.EntityGraphType.LOAD)
    Optional<CatalogEntity> findByCode(String code);
}
