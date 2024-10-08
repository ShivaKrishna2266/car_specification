package com.car_specification.car.service;

import com.car_specification.car.dto.CarColourDTO;

import java.util.List;

public interface CarColourService {

    List<CarColourDTO> getAllColours();

    CarColourDTO getCarColourById(Integer colourId);

    CarColourDTO createColourById(CarColourDTO carColourDTO);

    CarColourDTO updateColourById(Integer colourId, CarColourDTO carColourDTO);

    Void deleteColourById(Integer colourId);
}
