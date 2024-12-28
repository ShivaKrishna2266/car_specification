package com.car_specification.car.repository;

import com.car_specification.car.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Integer> {
    @Query("SELECT cb FROM CarBrand cb WHERE cb.brandId = :brandId")
    List<CarBrand> findByCarBrandId(@Param("brandId") Integer brandId);

}
