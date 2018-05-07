package com.goone.mangone.api.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_chapter")
@Where(clause = "deleted_at is null")
public class ChapterEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    private Long chapterId;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "number")
    private Integer number;
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
