package com.car_specification.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarBrandDTO {
    private Integer brandId;
    private String brandName;
    private String countryOfOrigin;
    private Integer foundedYear;
    private String logoUrl;
    private String description;
}
