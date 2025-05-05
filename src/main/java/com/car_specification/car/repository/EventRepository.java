package com.car_specification.car.repository;

import com.car_specification.car.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Events ,Long> {

    Optional<Events> findByUser_UserId(Long userId);

//    Optional<Events> findEventsByUserId(Long userId);


    @Query("SELECT e FROM Events e WHERE e.user.userId = :userId")
    List<Events> findEventsByUserId(Long userId);
}
