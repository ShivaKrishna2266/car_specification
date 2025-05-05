package com.car_specification.car.repository;

import com.car_specification.car.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findByEvents_EventId(Long eventId);
}

