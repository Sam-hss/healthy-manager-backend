package com.hss.healthyManager.advice;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {

    /**
     * 错误返回信息
     */
    NO_CHECK_INFO(400, "暂无健康档案表，无法进行数据分析"),
    CHOOSE_FILE(400, "文件未上传"),
    IS_NOT_LOGIN(400, "用户未登录"),
    UPLOAD_FAIL(400, "上传失败"),
    RESOURCE_FOUNT_FAIL(400, "获取菜单权限失败！"),
    NOT_AUTHORIZED(403, "您暂无权限访问该资源！"),
    ACCOUNT_IS_EXIT(400, "用户名已存在"),
    PASSWORD_WRONG(400, "密码错误"),
    ACCOUNT_IS_NOT_EXIT(400, "用户名不存在"),
    UPDATE_ERROR(400, "更新失败"),
    ADD_ERROR(400, "新增失败"),
    DELETE_ERROR(400, "删除失败"),
    GET_LIST_ERROR(400, "获取列表失败"),
    GET_ITEM(400, "获取对象失败"),
    NO_WEIGHT_HEIGHT(400, "当前档案未完善身高体重信息");

    /**
     * 代码值
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
}
