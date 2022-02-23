package com.hss.healthyManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统基本信息
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "system_info")
public class SystemInfo extends BaseEntity {

    /**
     * 系统名称
     */
    @Column(nullable = false)
    private String systemName;
    /**
     * 系统版本
     */
    @Column
    private String version;
    /**
     * 最新通知
     */
    @Column
    private String notice;
}
