package com.car_specification.car.service;

import com.car_specification.car.dto.EventRegisterDTO;
import com.car_specification.car.exception.ApplicationBusinessException;

import java.util.List;

public interface EventRegisterServices {

    List<EventRegisterDTO> getAllEventRegisters();

    EventRegisterDTO getEventRegisterById(Long eventRegisterId);

    EventRegisterDTO createEventRegister(EventRegisterDTO eventRegisterDTO) throws ApplicationBusinessException;

    EventRegisterDTO updateEventRegister(Long eventRegisterId, EventRegisterDTO eventRegisterDTO);

    Void deleteEventRegisterById(Long eventRegisterId);

    boolean isUserRegistered(Integer userId, Long eventId);
    String registerUserForEvent(EventRegisterDTO eventRegisterDTO);
}
