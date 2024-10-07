package com.car_specification.car.mapper;

import com.car_specification.car.dto.AppointmentDTO;
import com.car_specification.car.entity.Appointment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class AppointmentMapper {
    public static final ModelMapper modelmapper =new ModelMapper();

    public  static AppointmentDTO convertToDTO(Appointment appointment){
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        BeanUtils.copyProperties(appointment,appointmentDTO);
        return appointmentDTO;

    }
    public  static  Appointment convertToEntity(AppointmentDTO appointmentDTO){
        Appointment appointment =new Appointment();
        BeanUtils.copyProperties(appointmentDTO,appointment);
        return appointment;
    }
}
