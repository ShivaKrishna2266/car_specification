package com.car_specification.car.service.impl;

import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.entity.CarBrand;
import com.car_specification.car.mapper.CarBrandMapper;
import com.car_specification.car.repository.CarBrandRepository;
import com.car_specification.car.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CarBrandServiceImpl implements CarBrandService {
    @Autowired
    private CarBrandRepository carBrandRepository;
    @Override
    public List<CarBrandDTO> getAllCarBrands() {
        return null;
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
    public CarBrandDTO createCarBrand(CarBrandDTO carBrandDTO) {
        return null;
    }

    @Override
    public CarBrandDTO updateCarBrand(Integer brandId, CarBrandDTO carBrandDTO) {
        return null;
    }

    @Override
    public Void deleteCarBrandById(Integer brandId) {
        return null;
    }
}
