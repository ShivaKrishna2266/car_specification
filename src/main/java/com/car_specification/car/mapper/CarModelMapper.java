package com.car_specification.car.mapper;

import com.car_specification.car.dto.CarModelDTO;
import com.car_specification.car.entity.CarModel;
import org.springframework.beans.BeanUtils;

public class CarModelMapper {
    public static CarModelDTO convertToDTO(CarModel carModel) {
        CarModelDTO carModelDTO = new CarModelDTO();
        BeanUtils.copyProperties(carModel, carModelDTO);

        // Map brandId from the nested CarBrand object
        if (carModel.getCarBrand() != null) {
            carModelDTO.setBrandId(carModel.getCarBrand().getBrandId());
        }

        return carModelDTO;
    }

    public static CarModel convertToEntity(CarModelDTO carModelDTO) {
        CarModel carModel = new CarModel();
        BeanUtils.copyProperties(carModelDTO, carModel);

        // Handle CarBrand mapping if needed
        // Ensure this is consistent with how you handle nested entities
        return carModel;
    }
}
