package com.hss.healthyManager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "role_resource_bind")
public class RoleResourceBind extends BaseEntity {

    @Column(nullable = false)
    private Integer roleId;

    @Column(nullable = false)
    private Integer resourceId;
}
