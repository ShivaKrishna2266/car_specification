package com.car_specification.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
    private Integer feedbackId;
    private String rating;
    private  String description;
    private Integer brandId;
    private Integer modelId;
    private Integer userId;
}
