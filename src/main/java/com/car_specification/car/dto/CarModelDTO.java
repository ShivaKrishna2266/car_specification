package com.car_specification.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDTO {
    private Integer modelId;
    private String modelName;
    private Double price;
    private String specifications;
    private Integer brandId;

    // 🔑 Key Features
    private String engine;
    private String transmission;
    private String infotainment;
    private String safety;

    // 🚗 Vehicle Details
    private String fuelType;
    private String bodyType;
    private int seatingCapacity;
    private String colorOptions;

    // 💰 Price Details
    private Double exShowroomPrice;
    private Double onRoadPrice;
    private Double insurance;
    private String emiOption;

    // Additional
    private String imageUrl;
    private Boolean isAvailable;

}
