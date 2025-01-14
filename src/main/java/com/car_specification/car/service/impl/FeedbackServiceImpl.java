package com.car_specification.car.service.impl;

import com.car_specification.car.dto.FeedbackDTO;
import com.car_specification.car.entity.CarBrand;
import com.car_specification.car.entity.CarModel;
import com.car_specification.car.entity.Feedback;
import com.car_specification.car.entity.User;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.mapper.FeedbackMapper;
import com.car_specification.car.repository.CarBrandRepository;
import com.car_specification.car.repository.CarModelRepository;
import com.car_specification.car.repository.FeedbackRepository;
import com.car_specification.car.repository.UserRepository;
import com.car_specification.car.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream().map(f -> FeedbackMapper.convertToDTO(f)).collect(Collectors.toList());
    }

    @Override
    public FeedbackDTO getFeedbackById(Integer feedbackId) {
        Optional<Feedback> findById = feedbackRepository.findById(feedbackId);
        if(findById.isPresent()){
           return FeedbackMapper.convertToDTO(findById.get());
        }
    return null;
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) throws ApplicationBusinessException {
        try {
            Feedback convertToEntity = FeedbackMapper.convertToEntity(feedbackDTO);
            convertToEntity.setCreatedBy("System");
            convertToEntity.setUpdatedBy("System");
            convertToEntity.setCreatedAt(LocalDateTime.now());
            convertToEntity.setUpdatedAt(LocalDateTime.now());

            Optional<CarBrand> carBrandOptional = carBrandRepository.findById(feedbackDTO.getBrandId());
            carBrandOptional.ifPresent(convertToEntity::setCarBrands);

            Optional<CarModel> carModelOptional = carModelRepository.findById(feedbackDTO.getModelId());
            carModelOptional.ifPresent(convertToEntity::setCarModel);

            Optional<User> userOptional = userRepository.findById(feedbackDTO.getUserId());
            userOptional.ifPresent(convertToEntity::setUsers);

            feedbackRepository.save(convertToEntity);
            return feedbackDTO;
        }catch (Exception e){
            throw new ApplicationBusinessException("Error while adding feedback");
        }
    }

    @Override
    public FeedbackDTO updateFeedback(Integer feedbackId, FeedbackDTO feedbackDTO) throws ApplicationBusinessException {
        try {
            Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackId);
            if(feedbackOptional.isPresent()){
                Feedback existingFeedBack =feedbackOptional.get();
                existingFeedBack.setRating(feedbackDTO.getRating());
                existingFeedBack.setDescription(feedbackDTO.getDescription());
                existingFeedBack.setFeedbackId(feedbackDTO.getFeedbackId());
                CarModel carModel = carModelRepository.findById(feedbackDTO.getModelId()).orElse(null);
                if(carModel != null){
                    existingFeedBack.setCarModel(carModel);
                }
                User user = userRepository.findById(feedbackDTO.getUserId()).orElse(null);
                if(user != null){
                    existingFeedBack.setUsers(user);
                }
                CarBrand carBrand = carBrandRepository.findById(feedbackDTO.getBrandId()).orElse(null);
                if(carBrand != null){
                    existingFeedBack.setCarBrands(carBrand);
                }
                Feedback updatedFeedback = feedbackRepository.save(existingFeedBack);

                return FeedbackMapper.convertToDTO(updatedFeedback);
            }
        }catch (Exception e){
            throw new ApplicationBusinessException("Error while updating feedback");
        }
        return null;
    }

    @Override
    public void deleteFeedbackById(Integer feedbackId) {
        if(feedbackRepository.existsById(feedbackId)){
            feedbackRepository.deleteById(feedbackId);
        }
    }
}
