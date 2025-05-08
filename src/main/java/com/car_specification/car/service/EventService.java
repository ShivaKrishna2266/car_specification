package com.car_specification.car.service;


import com.car_specification.car.dto.EventsDTO;
import com.car_specification.car.exception.ApplicationBusinessException;

import java.util.List;

public interface EventService {

    List<EventsDTO> getAllEvents();

    EventsDTO getEventById(Long eventId);

    EventsDTO createEvent(EventsDTO eventsDTO) throws ApplicationBusinessException;

    EventsDTO updateEvent(Long eventId, EventsDTO eventsDTO);

    Void deleteEventById(Long eventId);

    EventsDTO getEventByUserId (Long userId);


}
