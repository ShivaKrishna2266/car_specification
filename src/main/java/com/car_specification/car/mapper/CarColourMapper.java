package com.car_specification.car.mapper;

import com.car_specification.car.dto.CarColourDTO;
import com.car_specification.car.entity.CarColour;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class CarColourMapper {
    public static final ModelMapper modelMapper = new ModelMapper();
    public static CarColourDTO convertToDTO(CarColour carColour){
        CarColourDTO carColourDTO =new CarColourDTO();
        BeanUtils.copyProperties(carColour,carColourDTO);
        if (carColour.getCarModel() != null) {
            carColourDTO.setCarModelId(carColour.getCarModel().getModelId());
        }
        return carColourDTO;
    }
    public static  CarColour convertToEntity(CarColourDTO carColourDTO){
        CarColour carColour = new CarColour();
        BeanUtils.copyProperties(carColourDTO, carColour);
        return carColour;
    }
}
