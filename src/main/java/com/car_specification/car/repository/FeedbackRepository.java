package com.car_specification.car.repository;

import com.car_specification.car.entity.CarColour;
import com.car_specification.car.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    @Query("SELECT cm FROM Feedback cm JOIN FETCH cm.carBrands")
    List<CarColour> findAllWithCarBrand();
}
