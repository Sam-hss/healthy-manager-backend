package com.hss.healthyManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 健康文档阅读
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "health_document")
public class HealthDocument extends BaseEntity {

    /**
     * 发布日期
     */
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishData;
    /**
     * 作者
     */
    @Column
    private String author;
    /**
     * 文档标题
     */
    @Column
    private String book;
    /**
     * 浏览数量
     */
    @Column
    private String visitNum;
    /**
     * 文档描述
     */
    @Column(columnDefinition = "text")
    private String description;
    /**
     * 文档内容
     */
    @Column(columnDefinition = "longtext")
    private String content;
    /**
     * 是否发布
     */
    @Column
    private Integer isPublished;
}
