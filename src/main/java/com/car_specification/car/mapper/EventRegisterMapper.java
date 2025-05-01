package com.car_specification.car.mapper;

import com.car_specification.car.dto.EventRegisterDTO;
import com.car_specification.car.entity.EventRegister;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class EventRegisterMapper {

    public static  final ModelMapper modelMapper =new ModelMapper();
    public static EventRegisterDTO convertToDTO(EventRegister eventRegister){
        EventRegisterDTO eventRegisterDTO =new EventRegisterDTO();
        BeanUtils.copyProperties(eventRegister,eventRegisterDTO);

//        if (eventRegister.getEvents() != null) {
//            eventRegisterDTO.setEventId(eventRegister.getEvents().getEventId());
//        }
        return eventRegisterDTO;
    }
    public static EventRegister convertToEntity(EventRegisterDTO eventRegisterDTO){
        EventRegister eventRegister =new EventRegister();
        BeanUtils.copyProperties(eventRegisterDTO,eventRegister);
        return eventRegister;
    }
}
