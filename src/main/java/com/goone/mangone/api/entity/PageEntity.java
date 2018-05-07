package com.goone.mangone.api.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_page")
@Where(clause = "deleted_at is null")
public class PageEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "page_id")
    private Long pageId;
    @Column(name = "image")
    private String image;
    @Column(name = "number")
    private Integer number;
    @Column(name = "chapter_id")
    private Long chapterId;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "status")
    private Short status;
}
