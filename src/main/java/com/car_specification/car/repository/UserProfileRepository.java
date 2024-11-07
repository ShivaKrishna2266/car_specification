package com.car_specification.car.repository;

import com.car_specification.car.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile ,Integer> {
}
