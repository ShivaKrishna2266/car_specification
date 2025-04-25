package com.car_specification.car.service.impl;

import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.dto.EventsDTO;
import com.car_specification.car.entity.CarBrand;
import com.car_specification.car.entity.Events;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.CarBrandMapper;
import com.car_specification.car.mapper.EventsMapper;
import com.car_specification.car.repository.EventRepository;
import com.car_specification.car.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Override
    public List<EventsDTO> getAllEvents() {
        List<Events> eventsDTOs = eventRepository.findAll();
        return eventsDTOs.stream()
                .map(c -> EventsMapper.convertToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public EventsDTO getEventById(Long eventId) {
        Optional<Events> optionalEvents = eventRepository.findById(eventId);
        if (optionalEvents.isPresent()) {
            Events events = optionalEvents.get();
            return EventsMapper.convertToDTO(events);
        } else {
            return null;
        }
    }

    @Override
    public EventsDTO createEvent(EventsDTO eventsDTO) throws ApplicationBusinessException {
        try {
            Events events = EventsMapper.convertToEntity(eventsDTO);
            events.setCreatedBy("System");
            events.setCreatedAt(LocalDateTime.now());
            events.setUpdatedBy("System");
            events.setUpdatedAt(LocalDateTime.now());
            Events savedEvents = eventRepository.save(events);
            EventsDTO eventsDTO1 = EventsMapper.convertToDTO(savedEvents);
            return eventsDTO1;
        } catch (Exception e) {
            throw new ApplicationBusinessException("Error while creating Events", e);
        }
    }

    @Override
    public EventsDTO updateEvent(Long eventId, EventsDTO eventsDTO) {
        try {
            Optional<Events> optionalEvents = eventRepository.findById(eventId);
            if (optionalEvents.isPresent()){
                Events events = optionalEvents.get();
                events.setEventName(eventsDTO.getEventName());
                events.setDate(eventsDTO.getDate());
                events.setDescription(eventsDTO.getDescription());
                Events savedEvents = eventRepository.save(events);
                EventsDTO eventsDTO1= EventsMapper.convertToDTO(savedEvents);
                return eventsDTO1;
            }
        }catch (Exception e){
            throw new ApplicationBusinessException("Error while updating event", e);
        }
        return null;
    }

    @Override
    public Void deleteEventById(Long eventId) {
        if(eventRepository.existsById(eventId))
            eventRepository.deleteById(eventId);
        return null;
    }
}
