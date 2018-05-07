package com.goone.mangone.api.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_comic")
@Where(clause = "deleted_at is null")
public class ComicEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "comic_id")
    private Long comicId;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @ManyToMany(targetEntity=CategoryEntity.class)
    @JoinTable(name="tb_category_comic", joinColumns=@JoinColumn(name="comic_id"), inverseJoinColumns=@JoinColumn(name="category_id"))
    private List<CategoryEntity> categories;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "status")
    private Short status;

}
