package com.car_specification.car.repository;

import com.car_specification.car.entity.Events;
import com.car_specification.car.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findByEvents_EventId(Long eventId);

    // Custom query to get users with non-null event
    @Query("SELECT u FROM User u WHERE u.events IS NOT NULL")
    List<User> findAllUsersWithEvent();
}

