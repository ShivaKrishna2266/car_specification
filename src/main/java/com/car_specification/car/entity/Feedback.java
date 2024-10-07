package com.car_specification.car.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(name = "rating")
    private String rating;

    @Column(name = "description")
    private  String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrands;

    @ManyToOne
    @JoinColumn(name = "car_model_id")
    private CarModel carModel;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

}
