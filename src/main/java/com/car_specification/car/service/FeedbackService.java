package com.car_specification.car.service;

import com.car_specification.car.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {

    List<FeedbackDTO> getAllFeedbacks();

    FeedbackDTO getFeedbackById(Integer feedbackId);

    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO updateFeedback(Integer feedbackId, FeedbackDTO feedbackDTO);

    FeedbackDTO deleteFeedbackById(Integer feedbackId);
}
