package com.car_specification.car.repository;

import com.car_specification.car.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Events ,Long> {
}
