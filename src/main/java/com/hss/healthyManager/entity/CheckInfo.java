package com.hss.healthyManager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 体检表结果
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "check_info")
public class CheckInfo extends BaseEntity {
    /**
     * 关联学生id
     */
    @Column(nullable = false) //表示此字段在保存时必需有值
    private Integer userId;
    /**
     * 学院
     */
    @Column(nullable = false)
    private String college;
    /**
     * 专业
     */
    @Column(nullable = false)
    private String major;
    /**
     * 班级
     */
    @Column(nullable = false)
    private String grade;
    /**
     * 学号
     */
    @Column(nullable = false)
    private String stuNo;
    /**
     * 姓名
     */
    @Column(nullable = false)
    private String name;
    /**
     * 性别
     */
    @Column
    private String sex;
    /**
     * 生日
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
     * 既往医史
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '既往医史'")
    private String historyCheck;
    /**
     * 个人头像
     */
    @Column(columnDefinition = "longtext")
    private String photo;
    /**
     * 左视力
     */
    @Column
    private Double leftView;
    /**
     * 右视力
     */
    @Column
    private Double rightView;
    /**
     * 左边矫正视力
     */
    @Column
    private Double correctLeftView;
    /**
     * 右边矫正视力
     */
    @Column
    private Double correctRightView;
    /**
     * 其他眼疾
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '其他眼疾'")
    private String otherEyesSick;
    /**
     * 彩色图案及编码
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '彩色图案及编码'")
    private String colorOrCode;
    /**
     * 单色识别
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '单色识别'")
    private String singleColorJudge;
    /**
     * 左边听力
     */
    @Column
    private Double leftAudition;
    /**
     * 右边听力
     */
    @Column
    private Double rightAudition;
    /**
     * 耳疾
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '耳疾'")
    private String earSick;
    /**
     * 嗅觉
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '嗅觉'")
    private String smell;
    /**
     * 鼻窦
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '鼻窦'")
    private String paranasalSinus;
    /**
     * 咽喉
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '咽喉'")
    private String throat;
    /**
     * 口吃
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '口吃'")
    private String stammer;
    /**
     * 龋齿
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '龋齿'")
    private String toothSick;
    /**
     * 缺齿
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '缺齿'")
    private String hypodontia;
    /**
     * 齿槽
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '齿槽'")
    private String toothPlace;
    /**
     * 其他五官疾病
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '其他五官疾病'")
    private String otherSenseSick;
    /**
     * 眼科签字
     */
    @Column
    private String signForEyes;
    /**
     * 耳喉鼻科签字
     */
    @Column
    private String signForEar;
    /**
     * 口腔科签字
     */
    @Column
    private String signForMouth;
    /**
     * 身高
     */
    @Column
    private Double height;
    /**
     * 体重
     */
    @Column
    private Double weight;
    /**
     * 腰围
     */
    @Column
    private Double waistline;
    /**
     * 皮肤
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '皮肤'")
    private String skin;
    /**
     * 淋巴
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '淋巴'")
    private String lymph;
    /**
     * 甲状腺
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '甲状腺'")
    private String glandulaThyroidea;
    /**
     * 脊柱
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '脊柱'")
    private String spineBackbone;
    /**
     * 四肢
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '四肢'")
    private String allLegs;
    /**
     * 关节
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '关节'")
    private String arthrosis;
    /**
     * 平足
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '平足'")
    private String foot;
    /**
     * 其他外科疾病
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '其他外科疾病'")
    private String otherSurgerySick;
    /**
     * 外科签字
     */
    @Column
    private String signForSurgery;
    /**
     * 血压
     */
    @Column
    private Integer bloodPressure;
    /**
     * 脉搏
     */
    @Column
    private Integer pulse;
    /**
     * 发育营养
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '发育营养'")
    private String growth;
    /**
     * 神经及精神
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '神经及精神'")
    private String mind;
    /**
     * 肺及呼吸道
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '肺及呼吸道'")
    private String lung;
    /**
     * 心脏及血管
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '心脏及血管'")
    private String heart;
    /**
     * 肝
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '肝'")
    private String liver;
    /**
     * 脾
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '正常' COMMENT '脾'")
    private String spleen;
    /**
     * 其他内科疾病
     */
    @Column(columnDefinition = "varchar(255) DEFAULT '无' COMMENT '其他内科疾病'")
    private String otherInternalSick;
    /**
     * 内科医生签字
     */
    @Column
    private String signForInternal;
    /**
     * 化验检查
     */
    @Column
    private String assayCheck;
    /**
     * 化验员签字
     */
    @Column
    private String signForAssay;
    /**
     * 胸部放射检查
     */
    @Column
    private String chestCheck;
    /**
     * 胸部放射检查医生签字
     */
    @Column
    private String signForChest;
    /**
     * 其他检查
     */
    @Column
    private String otherCheck;
    /**
     * 检查结论
     */
    @Column
    private String checkResult;
    /**
     * 负责医生签字
     */
    @Column
    private String signForResult;
    /**
     * 检查医院意见
     */
    @Column
    private String suggestForCheck;
    /**
     * 盖章
     */
    @Column
    private String sealer;
    /**
     * 备注
     */
    @Column
    private String remark;
    /**
     * 体检日期
     */
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkTime;
    /**
     * 体检学年
     */
    @Column
    private String checkYear;
}
