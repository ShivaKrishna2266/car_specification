package com.car_specification.car.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class EventRegisterDTO {
    private Long eventRegisterId; // ID of the event registration
    private Integer userId;       // User ID of the registered user
    private Long eventId;         // Event ID of the event
    private String username;      // Username of the registered user
    private String email;         // Email of the registered user
    private String mobile;        // Mobile number of the registered user
    private LocalDateTime date;   // Registration date/time
    private Boolean isRegistered; // Registration status (true/false, nullable)

    // Event details
    private String eventName;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String imageUrl;
    private String category;
    private String organizerName;
    private String contactEmail;
    private String contactPhone;
    private String status;
    private Boolean isFree;
    private BigDecimal ticketPrice;
    private Integer attendeesCount;
    private String eventLink;
    private String bannerVideo;

    // Constructor for registration details only
    public EventRegisterDTO(Integer userId, String username, String email, String mobile, LocalDateTime date, Boolean isRegistered) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.date = date;
        this.isRegistered = isRegistered;
    }

    // Full constructor for JPQL result mapping
    @SuppressWarnings("unused")
    public EventRegisterDTO(
            Long eventRegisterId,
            Integer userId,
            Long eventId,
            String username,
            String email,
            String mobile,
            LocalDateTime date,
            Boolean isRegistered,
            String eventName,
            String description,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String location,
            String imageUrl,
            String category,
            String organizerName,
            String contactEmail,
            String contactPhone,
            String status,
            Boolean isFree,
            BigDecimal ticketPrice,
            Integer attendeesCount,
            String eventLink,
            String bannerVideo
    ) {
        this.eventRegisterId = eventRegisterId;
        this.userId = userId;
        this.eventId = eventId;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.date = date;
        this.isRegistered = isRegistered;
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.imageUrl = imageUrl;
        this.category = category;
        this.organizerName = organizerName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.status = status;
        this.isFree = isFree;
        this.ticketPrice = ticketPrice;
        this.attendeesCount = attendeesCount;
        this.eventLink = eventLink;
        this.bannerVideo = bannerVideo;
    }

    // Constructor to return only isRegistered
    public EventRegisterDTO(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}
