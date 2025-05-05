package com.car_specification.car.service.impl;

import com.car_specification.car.dto.EventsDTO;
import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.entity.Events;
import com.car_specification.car.entity.Feedback;
import com.car_specification.car.entity.User;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.EventsMapper;
import com.car_specification.car.mapper.FeedbackMapper;
import com.car_specification.car.mapper.UserMapper;
import com.car_specification.car.repository.UserRepository;
import com.car_specification.car.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAllUsersWithEvent(); // fetch users with non-null eventId
        return users.stream()
                .map(UserMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        Optional<User> users= userRepository.findById(userId);
        if(users.isPresent()){
            return  UserMapper.convertToDTO(users.get());
        }
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String username){

        Optional<User> users= userRepository.findByUsername(username);
        if(users.isPresent()){
            return  UserMapper.convertToDTO(users.get());
        }
        return null;
    }


    @Override
    public UserDTO createUser(UserDTO userDTO) throws ApplicationBusinessException {
        try{
            User convertToEntity = UserMapper.convertToEntity(userDTO);
            convertToEntity.setCreatedBy("System");
            convertToEntity.setUpdatedBy("System");
            convertToEntity.setCreatedAt(LocalDateTime.now());
            convertToEntity.setUpdatedAt(LocalDateTime.now());
            userRepository.save(convertToEntity);
            return userDTO;
        }catch (Exception e){
            throw new ApplicationBusinessException("Error while adding user");
        }
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO userDTO) {
        return null;
    }

    @Override
    public void deleteUserById(Integer userId) {

    }


    @Override
    public List<UserDTO> getUserByEventId(Long eventId) {
        // Fetch list of users associated with the event
        List<User> users = userRepository.findByEvents_EventId(eventId);

        if (users != null && !users.isEmpty()) {
            return users.stream()
                    .map(UserMapper::convertToDTO)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }


}
