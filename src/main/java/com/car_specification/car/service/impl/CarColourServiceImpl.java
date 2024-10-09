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

    @Autowired
    private CarColourRepository carColourRepository;
    @Autowired
    private CarModelRepository carModelRepository;
    @Override
    public List<CarColourDTO> getAllColours() {
        return carColourRepository.findAll()
                .stream()
                .map(CarColourMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarColourDTO getCarColourById(Integer colourId) {
        Optional<CarColour> optionalCarColour = carColourRepository.findById(colourId);
        if (optionalCarColour.isPresent()) {
            CarColour carColour = optionalCarColour.get();
            return CarColourMapper.convertToDTO(carColour);
        } else {
            return null;
        }
    }

    @Override
    public CarColourDTO createColour(CarColourDTO carColourDTO) {
        try {
            CarColour carColour = CarColourMapper.convertToEntity(carColourDTO);
            carColour.setCreatedBy("System");
            carColour.setCreatedAt(LocalDateTime.now());
            carColour.setUpdatedBy("System");
            carColour.setUpdatedAt(LocalDateTime.now());
            CarModel carModel = carModelRepository.findById(carColourDTO.getCarModelId()).orElse(null);
            if (carModel != null) {
                carColour.setCarModel(carModel);
            }
            CarColour savedCarColour = carColourRepository.save(carColour);
            CarColourDTO carColourDTO1 = CarColourMapper.convertToDTO(savedCarColour);
            carColourDTO1.setCarModelId(carColour.getCarModel().getModelId());
            return carColourDTO1;
        } catch (Exception e) {
            throw new ApplicationBusinessException("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public CarColourDTO updateColour(Integer colourId, CarColourDTO carColourDTO) {
        Optional<CarColour> optionalCarColour = carColourRepository.findById(colourId);
        if (optionalCarColour.isPresent()) {
            CarColour carColour = optionalCarColour.get();
            carColour.setColourName(carColourDTO.getColourName());
            CarColour updatedCarColor = carColourRepository.save(carColour);
            CarColourDTO carColourDTO1 = CarColourMapper.convertToDTO(updatedCarColor);
            carColourDTO1.setCarModelId(updatedCarColor.getCarModel().getModelId());
            return carColourDTO1;
        } else {
            return null;
        }
    }

    @Override
    public Void deleteColour(Integer colourId) {
        if (carColourRepository.existsById(colourId))
            carColourRepository.deleteById(colourId);
        return null;
    }
}
