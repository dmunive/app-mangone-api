package com.goone.mangone.api.dao;

import com.goone.mangone.api.entity.CategoryComicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryComicDao extends JpaRepository<CategoryComicEntity,Long> {
}
