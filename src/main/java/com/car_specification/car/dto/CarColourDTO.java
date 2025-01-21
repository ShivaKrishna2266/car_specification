package com.car_specification.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarColourDTO {
    private Integer colourId;
    private String colourName;
    private Integer modelId;
}
