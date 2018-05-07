package com.goone.mangone.api.dao;

import com.goone.mangone.api.entity.PageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PageDao extends JpaRepository<PageEntity,Long> {

    @Query("SELECT p FROM PageEntity p  " +
           "WHERE (:chapterId IS NULL OR p.chapterId = :chapterId) " +
           "AND (:number IS NULL OR p.number = :number) " +
           "AND (:status IS NULL OR p.status = :status) AND p.deletedAt IS NULL")
    Page<PageEntity> searchPage(@Param("chapterId") Long chapterId, @Param("number") Short number, @Param("status") Short status, Pageable pageable);

}
