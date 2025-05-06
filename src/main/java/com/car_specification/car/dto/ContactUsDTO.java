package com.car_specification.car.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactUsDTO {


    private Long id;
    private String requestType;
    private String name;
    private String email;
    private String country;
    private String enquiry;
}
