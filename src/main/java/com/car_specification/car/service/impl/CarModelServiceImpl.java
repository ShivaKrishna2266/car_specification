package com.car_specification.car.service.impl;

import com.car_specification.car.dto.CarModelDTO;
import com.car_specification.car.entity.CarBrand;
import com.car_specification.car.entity.CarModel;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.CarModelMapper;
import com.car_specification.car.repository.CarBrandRepository;
import com.car_specification.car.repository.CarModelRepository;
import com.car_specification.car.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CarModelServiceImpl implements CarModelService {
    @Autowired
    private CarModelRepository carModelRepository;
    @Autowired
    private CarBrandRepository carBrandRepository;
    @Override
    public List<CarModelDTO> getAllCarModels() {
        return carModelRepository.findAllWithBrand()
                .stream()
                .map(CarModelMapper::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public CarModelDTO getCarModelById(Integer modelId) {
        Optional<CarModel> optionalCarModel = carModelRepository.findById(modelId);
        if (optionalCarModel.isPresent()) {
            CarModel carModel = optionalCarModel.get();
            return CarModelMapper.convertToDTO(carModel);
        } else {
            return null;
        }
    }

    @Override
    public CarModelDTO createCarModel(CarModelDTO carModelDTO) throws ApplicationBusinessException {
        try {
            CarModel carModel = CarModelMapper.convertToEntity(carModelDTO);
            carModel.setCreatedBy("System");
            carModel.setCreatedAt(LocalDateTime.now());
            carModel.setUpdatedBy("System");
            carModel.setUpdatedAt(LocalDateTime.now());
            Optional<CarBrand> carBrandOptional = carBrandRepository.findById(carModelDTO.getBrandId());
            carBrandOptional.ifPresent(carModel::setCarBrand);
            CarModel savedCarModel = carModelRepository.save(carModel);
            CarModelDTO modelDTO = CarModelMapper.convertToDTO(savedCarModel);
            modelDTO.setBrandId(carModel.getCarBrand().getBrandId());
            return modelDTO;
        } catch (Exception e) {
            throw new ApplicationBusinessException("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public CarModelDTO updateCarModel(Integer modelId, CarModelDTO carModelDTO) {
        Optional<CarModel> optionalCarModel = carModelRepository.findById(modelId);
        if (optionalCarModel.isPresent()) {
            CarModel carModel = optionalCarModel.get();
            carModel.setModelName(carModelDTO.getModelName());
            carModel.setPrice(carModelDTO.getPrice());
            carModel.setSpecifications(carModelDTO.getSpecifications());

            // Key Features
            carModel.setEngine(carModelDTO.getEngine());
            carModel.setTransmission(carModelDTO.getTransmission());
            carModel.setInfotainment(carModelDTO.getInfotainment());
            carModel.setSafety(carModelDTO.getSafety());

            // Vehicle Details
            carModel.setFuelType(carModelDTO.getFuelType());
            carModel.setBodyType(carModelDTO.getBodyType());
            carModel.setSeatingCapacity(carModelDTO.getSeatingCapacity());
            carModel.setColorOptions(carModelDTO.getColorOptions());

            // Price Details
            carModel.setExShowroomPrice(carModelDTO.getExShowroomPrice());
            carModel.setOnRoadPrice(carModelDTO.getOnRoadPrice());
            carModel.setInsurance(carModelDTO.getInsurance());
            carModel.setEmiOption(carModelDTO.getEmiOption());

            // Additional Info
            carModel.setImageUrl(carModelDTO.getImageUrl());
            carModel.setIsAvailable(carModelDTO.getIsAvailable());

            carModel.setCreatedBy("System");
            carModel.setCreatedAt(LocalDateTime.now());
            carModel.setUpdatedBy("System");
            carModel.setUpdatedAt(LocalDateTime.now());


            CarBrand carBrand = carBrandRepository.findById(carModelDTO.getBrandId()).orElse(null);
            if (carBrand != null) {
                carModel.setCarBrand(carBrand);
            }
            CarModel savedCarModel = carModelRepository.save((carModel));
            CarModelDTO carModelDTO1 = CarModelMapper.convertToDTO(savedCarModel);
            carModelDTO1.setBrandId(savedCarModel.getCarBrand().getBrandId());
            return carModelDTO1;
        } else {
            return null;
        }
    }

    @Override
    public Void deleteCarModelById(Integer modelId) {
        if (carModelRepository.existsById(modelId))
            carModelRepository.deleteById(modelId);
        return null;
    }

    @Override
    public List<CarModelDTO> getAllModelsByBrandId(String brandName) {
       List<CarModel> carModels = carModelRepository.findByCarBrandName(brandName);
       List<CarModelDTO> carModelDTOS = new ArrayList<>();
       for(CarModel carModel : carModels){
           CarModelDTO dto = CarModelMapper.convertToDTO(carModel);
           carModelDTOS.add(dto);
       }
       return  carModelDTOS;

    }

}
