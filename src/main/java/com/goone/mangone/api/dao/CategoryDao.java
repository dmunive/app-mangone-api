package com.goone.mangone.api.dao;

import com.goone.mangone.api.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<CategoryEntity,Long> {

    @Query("SELECT c FROM CategoryEntity c  " +
           "WHERE (:name IS NULL OR c.name LIKE CONCAT('%',:name,'%')) " +
           "AND (:status IS NULL OR c.status = :status) AND c.deletedAt IS NULL")
    Page<CategoryEntity> searchCategory(@Param("name") String name, @Param("status") Short status, Pageable pageable);

    Optional<CategoryEntity> findByName(String name);

    Optional<CategoryEntity> findByCategoryIdNotAndName(Long categoryId, String name);
}
