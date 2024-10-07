package com.car_specification.car.mapper;

import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.entity.CarBrand;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class CarBrandMapper {
    public static final ModelMapper modelMapper = new ModelMapper();

    public static CarBrandDTO convertToDTO(CarBrand carBrand){
      CarBrandDTO carBrandDTO = new CarBrandDTO();
        BeanUtils.copyProperties(carBrand ,carBrandDTO);
        return carBrandDTO;

    }
    public static CarBrand convertToEntity  (CarBrandDTO carBrandDTO) {
        CarBrand carBrand = new CarBrand();
        BeanUtils.copyProperties(carBrandDTO ,carBrand);
        return carBrand;

    }
}
