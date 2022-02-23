package com.hss.healthyManager.utils.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HealthDTO {

    private Double bim;

    private String suggestion;
}
