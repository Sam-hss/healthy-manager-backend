package com.hss.healthyManager.service;

import com.hss.healthyManager.entity.Suggestion;
import com.hss.healthyManager.utils.dto.InfoDTO;
import com.hss.healthyManager.utils.dto.InfoReadDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SuggestionService extends BaseService<Suggestion> {

    /**
     * 获取用户为读消息
     *
     * @param userId 用户id
     * @return 用户为读消息
     */
    List<Suggestion> getUnReadMessageByUserId(Integer userId);

    /**
     * 返回分页数据
     *
     * @param infoDTO 条件
     * @return 分页数据
     */
    PageInfo<Suggestion> getPage(InfoDTO infoDTO);

    /**
     * 将对应id的消息的状态
     */
    Integer markToRead(InfoReadDTO infoReadDTO);

    /**
     * 批量删除消息
     *
     * @param ids id数组
     * @return 操作成功数量
     */
    Integer deleteInfoByIds(Integer[] ids);
}
