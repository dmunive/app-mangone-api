package com.goone.mangone.api.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_category_comic")
@Where(clause = "deleted_at is null")
public class CategoryComicEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "category_comic_id")
    private Long categoryComicId;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "comic_id")
    private Long comicId;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "status")
    private Short status;
}
