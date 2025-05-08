package com.car_specification.car.mapper;

import com.car_specification.car.dto.EventRegisterDTO;
import com.car_specification.car.entity.EventRegister;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class EventRegisterMapper {

    public static final ModelMapper modelMapper = new ModelMapper();

    public static EventRegisterDTO convertToDTO(EventRegister eventRegister) {
        EventRegisterDTO dto = new EventRegisterDTO();

        dto.setEventRegisterId(eventRegister.getEventRegisterId());
        dto.setIsRegistered(eventRegister.getIsRegistered());
        dto.setEmail(eventRegister.getEmail());
        dto.setMobile(eventRegister.getMobile());
        dto.setDate(eventRegister.getDate());
        dto.setUsername(eventRegister.getUsername());


        if (eventRegister.getEvents() != null) {
            dto.setEventId(eventRegister.getEvents().getEventId());
        }
        if (eventRegister.getUser() != null) {
            dto.setUserId(eventRegister.getUser().getUserId());
        }
        return dto;
    }

    public static EventRegister convertToEntity(EventRegisterDTO eventRegisterDTO) {
        EventRegister eventRegister = new EventRegister();
        BeanUtils.copyProperties(eventRegisterDTO, eventRegister);
        return eventRegister;
    }

    public static List<EventRegisterDTO> convertToDTOList(List<EventRegister> list) {
        return list.stream().map(EventRegisterMapper::convertToDTO).collect(Collectors.toList());
    }

}
