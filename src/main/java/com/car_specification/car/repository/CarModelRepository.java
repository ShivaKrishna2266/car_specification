package com.car_specification.car.repository;
import com.car_specification.car.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    @Query("SELECT cm FROM CarModel cm WHERE cm.carBrands.brandId = :carBrandId")
    List<CarModel> findByCarBrandId(@Param("carBrandId") Integer carBrandId);

    @Query("SELECT cm FROM CarModel cm WHERE cm.carBrands.brandName = :brandName")
    List<CarModel> findByCarBrandName(@Param("brandName") String brandName);

}
