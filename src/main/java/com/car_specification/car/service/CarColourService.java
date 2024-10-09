package com.car_specification.car.service;

import com.car_specification.car.dto.CarColourDTO;
import com.car_specification.car.exception.ApplicationBusinessException;

import java.util.List;

public interface CarColourService {

    List<CarColourDTO> getAllColours();

    CarColourDTO getCarColourById(Integer colourId);

    CarColourDTO createColour(CarColourDTO carColourDTO);

    CarColourDTO updateColour(Integer colourId, CarColourDTO carColourDTO);

    Void deleteColour(Integer colourId);
}
