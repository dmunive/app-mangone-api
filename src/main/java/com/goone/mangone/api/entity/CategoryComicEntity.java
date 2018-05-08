package com.goone.mangone.api.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_category_comic")
public class CategoryComicEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "category_comic_id")
    private Long categoryComicId;
    @Column(name = "category_id")
    private Long categoryId;
    @Column(name = "comic_id")
    private Long comicId;
}
