package com.car_specification.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsDTO {

    private Long eventId;
    private String eventName;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String imageUrl;
    private String category;
    private String organizerName;
    private String contactEmail;
    private String contactPhone;
    private String status; // "Upcoming", "Ongoing", "Completed"
    private Boolean isFree;
    private Double ticketPrice;
    private Integer attendeesCount;
    private String eventLink;
    private String bannerVideo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long userId;


}
