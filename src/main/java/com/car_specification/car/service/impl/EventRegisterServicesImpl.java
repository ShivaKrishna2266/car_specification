package com.car_specification.car.service.impl;

import com.car_specification.car.dto.CarModelDTO;
import com.car_specification.car.dto.EventRegisterDTO;
import com.car_specification.car.entity.CarBrand;
import com.car_specification.car.entity.CarModel;
import com.car_specification.car.entity.EventRegister;
import com.car_specification.car.entity.Events;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.CarModelMapper;
import com.car_specification.car.mapper.EventRegisterMapper;
import com.car_specification.car.repository.EventRegisterRepository;
import com.car_specification.car.repository.EventRepository;
import com.car_specification.car.service.EventRegisterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventRegisterServicesImpl implements EventRegisterServices {


    @Autowired
    private EventRegisterRepository eventRegisterRepository;

    @Autowired
    private EventRepository eventRepository;
    @Override
    public List<EventRegisterDTO> getAllEventRegisters() {
        return eventRegisterRepository.findAll()
                .stream()
                .map(EventRegisterMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EventRegisterDTO getEventRegisterById(Long eventRegisterId) {
        return null;
    }

    @Override
    public EventRegisterDTO createEventRegister(EventRegisterDTO eventRegisterDTO) throws ApplicationBusinessException {
        try {
            EventRegister eventRegister = EventRegisterMapper.convertToEntity(eventRegisterDTO);
            eventRegister.setCreatedBy("System");
            eventRegister.setCreatedAt(LocalDateTime.now());
            eventRegister.setUpdatedBy("System");
            eventRegister.setUpdatedAt(LocalDateTime.now());

            Events events = eventRepository.findById(eventRegisterDTO.getEventId()) .orElseThrow(() -> new RuntimeException("Event not found"));
            eventRegister.setEvents(events);

            EventRegister savedEventregister = eventRegisterRepository.save(eventRegister);
            EventRegisterDTO eventRegisterDTO1 = EventRegisterMapper.convertToDTO(savedEventregister);
            eventRegisterDTO1.setEventId(eventRegister.getEvents().getEventId());
            return eventRegisterDTO1;
        } catch (Exception e) {
            throw new ApplicationBusinessException("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public EventRegisterDTO updateEventRegister(Long eventRegisterId, EventRegisterDTO eventRegisterDTO) {
        return null;
    }

    @Override
    public Void deleteEventRegisterById(Long eventRegisterId) {
        return null;
    }
}
