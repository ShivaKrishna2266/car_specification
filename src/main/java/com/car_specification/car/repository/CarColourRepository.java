package com.car_specification.car.repository;

import com.car_specification.car.entity.CarColour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarColourRepository extends JpaRepository<CarColour, Integer> {
}
