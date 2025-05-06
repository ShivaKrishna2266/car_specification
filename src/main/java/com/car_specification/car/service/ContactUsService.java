package com.car_specification.car.service;

import com.car_specification.car.dto.ContactUsDTO;
import com.car_specification.car.exception.ApplicationBusinessException;

import java.util.List;

public interface ContactUsService {

    List<ContactUsDTO> getAllContacts();

    ContactUsDTO getContactById(Long id);

    ContactUsDTO createContact(ContactUsDTO contactUsDTO) throws ApplicationBusinessException;

    ContactUsDTO updateContact(Long id, ContactUsDTO contactUsDTO);

    void deleteContactById(Long id);
}
