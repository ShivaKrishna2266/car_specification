package com.car_specification.car.repository;
import com.car_specification.car.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    @Query("SELECT cm FROM CarModel cm WHERE cm.carBrand.brandId = :brandId")
    List<CarModel> findByCarBrandBrandId(@Param("brandId") Integer brandId);

    @Query("SELECT cm FROM CarModel cm WHERE cm.carBrand.brandName = :brandName")
    List<CarModel> findByCarBrandName(@Param("brandName") String brandName);

    @Query("SELECT cm FROM CarModel cm JOIN FETCH cm.carBrand")
    List<CarModel> findAllWithBrand();


}
