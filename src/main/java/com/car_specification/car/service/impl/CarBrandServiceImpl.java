package com.car_specification.car.service.impl;

import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.entity.CarBrand;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.CarBrandMapper;
import com.car_specification.car.repository.CarBrandRepository;
import com.car_specification.car.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarBrandServiceImpl implements CarBrandService {
    @Autowired
    private CarBrandRepository carBrandRepository;
    @Override
    public List<CarBrandDTO> getAllCarBrands() {
            List<CarBrand> carBrandDTOS = carBrandRepository.findAll();
            return carBrandDTOS.stream()
                                .map(c ->CarBrandMapper.convertToDTO(c))
                                .collect(Collectors.toList());
    }
    @Override
    public CarBrandDTO getCarBrandById(Integer brandId) {
        Optional<CarBrand> optionalCarBrand = carBrandRepository.findById(brandId);
        if (optionalCarBrand.isPresent()) {
            CarBrand carBrand = optionalCarBrand.get();
            return CarBrandMapper.convertToDTO(carBrand);
        } else {
            return null;
        }
    }
    @Override
    public CarBrandDTO createCarBrand(CarBrandDTO carBrandDTO) throws ApplicationBusinessException {
        try {
            CarBrand carBrand = CarBrandMapper.convertToEntity(carBrandDTO);
            carBrand.setBrandName(carBrandDTO.getBrandName());
            carBrand.setCreatedBy("System");
            carBrand.setCreatedAt(LocalDateTime.now());
            carBrand.setUpdatedBy("System");
            carBrand.setUpdatedAt(LocalDateTime.now());
            CarBrand savedCarBrand = carBrandRepository.save(carBrand);
            CarBrandDTO carBrandDTO1 = CarBrandMapper.convertToDTO(savedCarBrand);
            return carBrandDTO1;
        } catch (Exception e) {
            throw new ApplicationBusinessException("Error while creating carBrand", e);
        }
    }

    @Override
    public CarBrandDTO updateCarBrand(Integer brandId, CarBrandDTO carBrandDTO) throws ApplicationBusinessException{
        try {
           Optional<CarBrand> optionalCarBrand = carBrandRepository.findById(brandId);
           if (optionalCarBrand.isPresent()){
               CarBrand carBrand = optionalCarBrand.get();
               carBrand.setBrandName(carBrandDTO.getBrandName());
               CarBrand savedCarBrand = carBrandRepository.save(carBrand);
               CarBrandDTO carBrandDTO1= CarBrandMapper.convertToDTO(savedCarBrand);
               return carBrandDTO1;
           }
        }catch (Exception e){
            throw new ApplicationBusinessException("Error while updating carBrand", e);
        }

        return null;
    }
    @Override
    public Void deleteCarBrandById(Integer brandId) {
        if(carBrandRepository.existsById(brandId))
            carBrandRepository.deleteById(brandId);
        return null;
    }

}
