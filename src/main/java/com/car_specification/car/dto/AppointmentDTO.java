package com.car_specification.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Integer appointmentId;
    private LocalDateTime createdDate;
    private LocalDateTime appointmentDate;
    private Integer userId;
}
