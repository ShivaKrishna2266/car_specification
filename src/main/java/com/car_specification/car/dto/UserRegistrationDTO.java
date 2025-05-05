package com.car_specification.car.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    private Integer userId;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String role;
    private Long eventId;
    private LocalDate registrationDate;

//    private Integer userId;
//    private String username;
//    private String email;
//    private String mobile;

}
