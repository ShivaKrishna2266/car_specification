package com.car_specification.car.service;

import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.exception.ApplicationBusinessException;

import java.util.List;

public interface CarBrandService {

    List<CarBrandDTO> getAllCarBrands();

    CarBrandDTO getCarBrandById(Integer brandId);

    CarBrandDTO createCarBrand(CarBrandDTO carBrandDTO) throws ApplicationBusinessException;

    CarBrandDTO updateCarBrand(Integer brandId, CarBrandDTO carBrandDTO);

    Void deleteCarBrandById(Integer brandId);


}
