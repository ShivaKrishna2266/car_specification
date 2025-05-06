package com.car_specification.car.repository;

import com.car_specification.car.entity.EventRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRegisterRepository extends JpaRepository<EventRegister , Long> {

    boolean existsByUser_UserIdAndEvents_EventId(Integer userId, Long eventId);
}
