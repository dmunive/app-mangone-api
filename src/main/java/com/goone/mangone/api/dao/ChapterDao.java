package com.goone.mangone.api.dao;

import com.goone.mangone.api.entity.ChapterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterDao extends JpaRepository<ChapterEntity,Long> {

    @Query("SELECT c FROM ChapterEntity c  " +
           "WHERE (:comicId IS NULL OR c.comicId = :comicId) " +
           "AND (:name IS NULL OR c.name LIKE CONCAT('%',:name,'%')) " +
           "AND (:number IS NULL OR c.number = :number) " +
           "AND (:status IS NULL OR c.status = :status) AND c.deletedAt IS NULL")
    Page<ChapterEntity> searchChapter(@Param("comicId") Long comicId, @Param("name") String name, @Param("number") Integer number, @Param("status") Short status, Pageable pageable);

}
