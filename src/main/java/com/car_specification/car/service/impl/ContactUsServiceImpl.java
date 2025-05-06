package com.car_specification.car.service.impl;

import com.car_specification.car.dto.ContactUsDTO;
import com.car_specification.car.entity.ContactUs;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.ContactUsMapper;
import com.car_specification.car.repository.ContactUsRepository;
import com.car_specification.car.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;


    @Override
    public List<ContactUsDTO> getAllContacts() {
        List<ContactUs> contactUses = contactUsRepository.findAll();
        return contactUses
                .stream()
                .map(c -> ContactUsMapper.convertToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public ContactUsDTO getContactById(Long id) {
        Optional<ContactUs> findById = contactUsRepository.findById(id);
        if(findById.isPresent()){
            return ContactUsMapper.convertToDTO(findById.get());
        }
        return null;
    }

    @Override
    public ContactUsDTO createContact(ContactUsDTO contactUsDTO) throws ApplicationBusinessException {
        try {
            ContactUs contactUs = ContactUsMapper.convertToEntity(contactUsDTO);
            contactUs.setCreatedBy("System");
            contactUs.setCreatedAt(LocalDateTime.now());
            contactUs.setUpdatedBy("System");
            contactUs.setUpdatedAt(LocalDateTime.now());
            ContactUs saveContactUs = contactUsRepository.save(contactUs);
            ContactUsDTO contactUsDTO1 = ContactUsMapper.convertToDTO(saveContactUs);
            return contactUsDTO1;
        } catch (Exception e) {
            throw new ApplicationBusinessException("Error while creating Events", e);
        }
    }

    @Override
    public ContactUsDTO updateContact(Long id, ContactUsDTO contactUsDTO) {
        return null;
    }

    @Override
    public void deleteContactById(Long id) {

    }
}
