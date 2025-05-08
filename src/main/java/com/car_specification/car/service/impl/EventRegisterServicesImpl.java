package com.car_specification.car.service.impl;

import com.car_specification.car.dto.EventRegisterDTO;
import com.car_specification.car.entity.EventRegister;
import com.car_specification.car.entity.Events;
import com.car_specification.car.entity.User;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.EventRegisterMapper;
import com.car_specification.car.repository.EventRegisterRepository;
import com.car_specification.car.repository.EventRepository;
import com.car_specification.car.repository.UserRepository;
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


    @Autowired
    private UserRepository userRepository;


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

    @Override
    public boolean isUserRegistered(Integer userId, Long eventId) {
        return eventRegisterRepository.existsByUser_UserIdAndEvents_EventId(userId, eventId);
    }

    @Override
    public String registerUserForEvent(EventRegisterDTO eventRegisterDTO) {
        if (isUserRegistered(eventRegisterDTO.getUserId(), eventRegisterDTO.getEventId())) {
            return "User already registered";
        }

        User user = userRepository.findById(eventRegisterDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Events event = eventRepository.findById(eventRegisterDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        EventRegister registration = new EventRegister();
        registration.setUser(user);
        registration.setEvents(event);
        registration.setDate(LocalDateTime.now());
        registration.setCreatedAt(LocalDateTime.now());
        registration.setUpdatedAt(LocalDateTime.now());
        registration.setCreatedBy("System");
        registration.setUpdatedBy("System");

        eventRegisterRepository.save(registration);
        return "User registered successfully";
    }

    @Override
    public List<EventRegisterDTO> getRegisteredUsersByEventId(Long eventId) {
        return eventRegisterRepository.findRegisteredUsersByEventId(eventId);
    }

    @Override
    public List<EventRegisterDTO> findRegisteredEventsByUserId(Integer userId) {
        return eventRegisterRepository.findRegisteredEventsByUserId(userId);
    }

}
