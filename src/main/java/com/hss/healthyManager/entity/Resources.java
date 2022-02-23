package com.hss.healthyManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 菜单资源和权限
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "resources")
@Entity
public class Resources extends BaseEntity {
    /**
     * 资源名称
     */
    @Column(nullable = false)
    private String name;
    /**
     * 按钮或菜单 0-menu  1-button
     */
    @Column(columnDefinition = "int default 0")
    private Integer type;
    /**
     * 权限
     */
    @Column
    private String permission;
    /**
     * 路由地址
     */
    @Column
    private String url;
    /**
     * 父级id  0-表示顶级
     */
    @Column(columnDefinition = "int default 0")
    private Integer parentId;

    /**
     * 排序  数值越小越靠前
     */
    @Column(columnDefinition = "int default 0")
    private Integer sort;

    @Column
    private String icon;
}
