package com.hss.healthyManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 医生建议
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "suggestion")
public class Suggestion extends BaseEntity {

    /**
     * 体检表id
     */
    @Column(nullable = false)
    private Integer checkInfoId;
    /**
     * 体检人id
     */
    @Column
    private Integer userId;
    /**
     * 医生id
     */
    @Column(nullable = false)
    private Integer doctorId;
    /**
     * 所属科室
     */
    @Column(nullable = false)
    private String office;
    /**
     * 建议内容
     */
    @Column(nullable = false)
    private String content;
    /**
     * 是否阅读
     */
    @Column(columnDefinition = "int default 0")
    private Integer isRead;
}
