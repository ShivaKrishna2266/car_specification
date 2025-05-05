package com.car_specification.car.repository;

import com.car_specification.car.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Events ,Long> {

//    Optional<Events> findByUserId(Long userId);
    Optional<Events> findByUser_UserId(Long userId);
}
