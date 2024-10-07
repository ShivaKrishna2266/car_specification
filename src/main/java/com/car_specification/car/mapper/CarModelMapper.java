package com.car_specification.car.mapper;

import com.car_specification.car.dto.CarColourDTO;
import com.car_specification.car.dto.CarModelDTO;
import com.car_specification.car.entity.CarColour;
import com.car_specification.car.entity.CarModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class CarModelMapper {
    public static  final ModelMapper modelMapper = new ModelMapper();
    public  static CarModelDTO convertToDTO(CarModel carModel){
        CarModelDTO carModelDTO = new CarModelDTO();
        BeanUtils.copyProperties(carModel, carModelDTO);
        return carModelDTO;
    }
    public static CarModel convertToEntity(CarModelDTO carModelDTO){
        CarModel carModel = new CarModel();
        BeanUtils.copyProperties(carModelDTO, carModel);
        return carModel;
    }
}
