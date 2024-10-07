package com.car_specification.car.service;

import com.car_specification.car.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDTO> getAllAppointments();

    AppointmentDTO getAppointmentById(Integer appointmentId);

    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);

    AppointmentDTO updateAppointment(Integer appointmentId, AppointmentDTO appointmentDTO);

    AppointmentDTO deleteAppointmentById(Integer appointmentId);

}
