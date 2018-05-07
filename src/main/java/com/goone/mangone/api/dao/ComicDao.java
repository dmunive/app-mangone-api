package com.goone.mangone.api.dao;

import com.goone.mangone.api.entity.ComicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComicDao extends JpaRepository<ComicEntity,Long> {

    @Query("SELECT c FROM ComicEntity c  " +
           "WHERE (:name IS NULL OR c.name LIKE CONCAT('%',:name,'%')) " +
           "AND (:status IS NULL OR c.status = :status) AND c.deletedAt IS NULL")
    Page<ComicEntity> searchComic(@Param("name") String name, @Param("status") Short status, Pageable pageable);

    Optional<ComicEntity> findByName(String name);

    Optional<ComicEntity> findByComicIdNotAndName(Long comicId, String name);
}
