package com.car_specification.car.service.impl;

import com.car_specification.car.dto.CarColourDTO;
import com.car_specification.car.entity.CarColour;
import com.car_specification.car.entity.CarModel;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.CarColourMapper;
import com.car_specification.car.repository.CarColourRepository;
import com.car_specification.car.repository.CarModelRepository;
import com.car_specification.car.service.CarColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarColourServiceImpl implements CarColourService {

    @Override
    public List<CarColourDTO> getAllColours() {
        return null;
    }

    @Override
    public CarColourDTO getCarColourById(Integer colourId) {
        return null;
    }

    @Override
    public CarColourDTO createColour(CarColourDTO carColourDTO) throws ApplicationBusinessException {
        return null;
    }

    @Override
    public CarColourDTO updateColour(Integer colourId, CarColourDTO carColourDTO) {
        return null;
    }

    @Override
    public Void deleteColour(Integer colourId) {
        return null;
    }
}
