package com.hss.healthyManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;
import java.util.Date;

/**
 * 实体的基类
 */
@Data
@MappedSuperclass
public class BaseEntity {

    /**
     * 唯一标识
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDatetime;

    /**
     * 修改时间
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDatetime;
}
