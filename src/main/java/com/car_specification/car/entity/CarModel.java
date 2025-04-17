package com.car_specification.car.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "car_model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "price")
    private Double price;

    @Column(name = "specifications")
    private String specifications;



    // 🔑 Key Features
    @Column(name = "engine")
    private String engine;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "infotainment")
    private String infotainment;

    @Column(name = "safety")
    private String safety;

    // 🚗 Vehicle Details
    @Column(name = "fuelType")
    private String fuelType;

    @Column(name = "bodyType")
    private String bodyType;

    @Column(name = "seatingCapacity")
    private int seatingCapacity;

    @Column(name = "colorOptions")
    private String colorOptions;

    // 💰 Price Details
    @Column(name = "exShowroomPrice")
    private Double exShowroomPrice;

    @Column(name = "onRoadPrice")
    private Double onRoadPrice;

    @Column(name = "insurance")
    private Double insurance;

    @Column(name = "emiOption")
    private String emiOption;

    // 🆕 Additional Fields

    @Column(name = "imageUrl")
    private String imageUrl; // Optional

    @Column(name = "isAvailable")
    private Boolean isAvailable = true;




    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand;
}
