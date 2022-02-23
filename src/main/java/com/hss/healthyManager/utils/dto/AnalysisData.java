package com.hss.healthyManager.utils.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AnalysisData {

    List<String> label;

    List<Double> height;

    List<Double> weight;

}
