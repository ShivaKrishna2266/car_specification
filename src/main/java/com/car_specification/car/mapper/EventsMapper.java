package com.car_specification.car.mapper;

import com.car_specification.car.dto.EventsDTO;
import com.car_specification.car.entity.Events;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class EventsMapper {

    public static final ModelMapper modelMapper = new ModelMapper();
    public static EventsDTO convertToDTO(Events events){
        EventsDTO eventsDTO =new EventsDTO();
        BeanUtils.copyProperties(events,eventsDTO);
        return eventsDTO;
    }
    public static  Events convertToEntity(EventsDTO eventsDTO){
        Events events = new Events();
        BeanUtils.copyProperties(eventsDTO, events);
        return events;
    }
}
