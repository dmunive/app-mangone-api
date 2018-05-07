package com.goone.mangone.api.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NamedEntityGraphs({
    @NamedEntityGraph(
        name = "Catalog.CatalogDetail",
        attributeNodes = {
                @NamedAttributeNode("catalogDetails")
        }
    )
})
@Table(name = "tb_catalog")
@Where(clause = "deleted_at is null")
public class CatalogEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private Long catalogId;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "catalog_id")
    @OneToMany
    private List<CatalogDetailEntity> catalogDetails;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "deleted_at")
    private Date deletedAt;
    @Column(name = "status")
    private Short status;
}
