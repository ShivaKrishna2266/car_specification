package com.car_specification.car.mapper;

import com.car_specification.car.dto.ContactUsDTO;
import com.car_specification.car.entity.ContactUs;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class ContactUsMapper {

    public static final ModelMapper modelMapper = new ModelMapper();
    public static ContactUsDTO convertToDTO(ContactUs contactUs){
        ContactUsDTO contactUsDTO =new ContactUsDTO();
        BeanUtils.copyProperties(contactUs,contactUsDTO);
        return contactUsDTO;
    }
    public static  ContactUs convertToEntity(ContactUsDTO contactUsDTO){
        ContactUs contactUs = new ContactUs();
        BeanUtils.copyProperties(contactUsDTO, contactUs);
        return contactUs;
    }
}
