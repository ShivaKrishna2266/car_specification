package com.car_specification.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegisterDTO {

    private Long eventRegisterId;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private LocalDateTime date;
    private String ticket;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;

    private Long eventId;
}
