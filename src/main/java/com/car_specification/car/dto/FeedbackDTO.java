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
    private Integer carBrandId;
    private Integer carModelId;
    private Integer userId;
}
