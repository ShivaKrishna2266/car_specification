package com.car_specification.car.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_us")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactUs {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String requestType;
        private String name;
        private String email;
        private String country;
        private String enquiry;
        private  String createdBy;
        private  String updatedBy;
        private LocalDateTime createdAt;
        private  LocalDateTime updatedAt;

}
