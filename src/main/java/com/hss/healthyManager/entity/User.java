package com.hss.healthyManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 密码
     */
    @Column
    private String password;

    /**
     * 角色id
     */
    @Column
    private Integer roleId;

    /**
     * 学院
     */
    @Column
    private String college;
    /**
     * 专业
     */
    @Column
    private String major;
    /**
     * 班级
     */
    @Column
    private String grade;
    /**
     * 学号
     */
    @Column
    private String stuNo;
    /**
     * 姓名
     */
    @Column
    private String name;
    /**
     * 性别
     */
    @Column
    private String sex;
    /**
     * 出生日期
     */
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birth;
    /**
     * 实际年龄
     */
    @Column
    private Integer realAge;
    /**
     * 文化程度
     */
    @Column
    private String cultureLevel;
    /**
     * 名族
     */
    @Column
    private String nation;
    /**
     * 职业
     */
    @Column
    private String occupation;
    /**
     * 籍贯
     */
    @Column
    private String nativePlace;
    /**
     * 现家庭住址
     */
    @Column
    private String presentAddress;
    /**
     * 毕业学校或工作单位;
     */
    @Column
    private String workPlace;

    /**
     * 辅导员id
     */
    @Column
    private Integer teacherId;
    /**
     * 个人头像
     */
    @Column(columnDefinition = "longtext")
    private String photo;
}
