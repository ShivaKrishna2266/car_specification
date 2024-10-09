package com.car_specification.car.service;

import com.car_specification.car.dto.FeedbackDTO;
import com.car_specification.car.exception.ApplicationBusinessException;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDTO> getAllFeedbacks();

    FeedbackDTO getFeedbackById(Integer feedbackId);

    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO updateFeedback(Integer feedbackId, FeedbackDTO feedbackDTO);

    void deleteFeedbackById(Integer feedbackId);
}
