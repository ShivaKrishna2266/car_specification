package com.car_specification.car.repository;

import com.car_specification.car.dto.EventRegisterDTO;
import com.car_specification.car.entity.EventRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRegisterRepository extends JpaRepository<EventRegister, Long> {

    // Checks if the user is registered for the event
    boolean existsByUser_UserIdAndEvents_EventId(Integer userId, Long eventId);

    // Fetch complete user details for registered users of a particular event
    @Query("SELECT new com.car_specification.car.dto.EventRegisterDTO(" +
            "er.user.userId, er.user.username, er.user.email, er.user.mobile, er.date, er.isRegistered) " +
            "FROM EventRegister er " +
            "WHERE er.events.eventId = :eventId")
    List<EventRegisterDTO> findRegisteredUsersByEventId(Long eventId);


    @Query("SELECT new com.car_specification.car.dto.EventRegisterDTO(" +
            "er.eventRegisterId, " +
            "u.userId, " +
            "e.eventId, " +
            "u.username, " +
            "u.email, " +
            "u.mobile, " +
            "er.date, " +
            "er.isRegistered, " +
            "e.eventName, " +
            "e.description, " +
            "CAST(CONCAT(e.date, 'T', e.startTime) AS java.time.LocalDateTime), " +
            "CAST(CONCAT(e.date, 'T', e.endTime) AS java.time.LocalDateTime), " +
            "e.location, " +
            "e.imageUrl, " +
            "e.category, " +
            "e.organizerName, " +
            "e.contactEmail, " +
            "e.contactPhone, " +
            "e.status, " +
            "e.isFree, " +
            "CAST(e.ticketPrice AS java.math.BigDecimal), " +
            "e.attendeesCount, " +
            "e.eventLink, " +
            "e.bannerVideo" +
            ") " +
            "FROM EventRegister er " +
            "JOIN er.user u " +
            "JOIN er.events e " +
            "WHERE u.userId = :userId")
    List<EventRegisterDTO> findRegisteredEventsByUserId(@Param("userId") Integer userId);
}
