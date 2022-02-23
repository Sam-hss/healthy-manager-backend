package com.hss.healthyManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色：学生，老师，医生，管理员
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Column(nullable = false)
    private String roleName;

    @Column
    private String remark;
}
