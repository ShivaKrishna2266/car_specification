package com.car_specification.car.repository;

import com.car_specification.car.entity.CarColour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarColourRepository extends JpaRepository<CarColour, Integer> {
    @Query("SELECT cm FROM CarColour cm JOIN FETCH cm.carModel")
    List<CarColour> findAllWithCarModel();
}
