package com.car_specification.car.service;

import com.car_specification.car.dto.CarModelDTO;

import java.util.List;

public interface CarModelService {

    List<CarModelDTO> getAllCarModels();

    CarModelDTO getCarModelById(Integer modelId);

    CarModelDTO createCarModel(CarModelDTO carModelDTO);

    CarModelDTO updateCarModel(Integer modelId, CarModelDTO carModelDTO);

    CarModelDTO deleteCarModelById(Integer modelId);
}
