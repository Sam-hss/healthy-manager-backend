package com.hss.healthyManager.utils.dto;

import lombok.Data;

@Data
public class InfoDTO {

    private Integer page;

    private Integer rows;

    private Integer userId;

    private Integer doctorId;

    private Integer isRead;

    private String content;

    private String office;
}
