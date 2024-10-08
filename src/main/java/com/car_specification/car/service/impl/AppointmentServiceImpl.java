package com.car_specification.car.service.impl;

import com.car_specification.car.dto.AppointmentDTO;
import com.car_specification.car.entity.Appointment;
import com.car_specification.car.entity.User;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.AppointmentMapper;
import com.car_specification.car.repository.AppointmentRepository;
import com.car_specification.car.repository.UserRepository;
import com.car_specification.car.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(Integer appointmentId) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            return AppointmentMapper.convertToDTO(appointment);
        } else {
            return null;
        }
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws ApplicationBusinessException {
        try {
            Appointment appointment = AppointmentMapper.convertToEntity(appointmentDTO);
            appointment.setCreatedBy("System");
            appointment.setCreatedAt(LocalDateTime.now());
            appointment.setUpdatedBy("System");
            appointment.setUpdatedAt(LocalDateTime.now());
            User user = userRepository.findById(appointmentDTO.getAppointmentId())
                    .orElse(null);
            if (user != null) {
                appointment.setUsers(user);
            }
            Appointment savedAppointment = appointmentRepository.save(appointment);
            AppointmentDTO appointmentDTO1 = AppointmentMapper.convertToDTO(savedAppointment);
            appointmentDTO1.setUserId(appointment.getUsers().getUserId());
            return appointmentDTO1;
        } catch (Exception e) {
            throw new ApplicationBusinessException("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public AppointmentDTO updateAppointment(Integer appointmentId, AppointmentDTO appointmentDTO) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setCreatedDate(appointmentDTO.getCreatedDate());
            appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
            Appointment savedAppointment = appointmentRepository.save((appointment));
            AppointmentDTO appointmentDTO1 = AppointmentMapper.convertToDTO(savedAppointment);
            appointmentDTO1.setUserId(savedAppointment.getUsers().getUserId());
            return appointmentDTO1;
        } else {
            return null;
        }
    }

    @Override
    public Void deleteAppointmentById(Integer appointmentId) {
        if (appointmentRepository.existsById(appointmentId))
            appointmentRepository.deleteById(appointmentId);
        return null;
    }
}
