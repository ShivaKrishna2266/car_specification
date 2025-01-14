package com.car_specification.car.mapper;

import com.car_specification.car.dto.FeedbackDTO;
import com.car_specification.car.entity.Feedback;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class FeedbackMapper {
    public static  final ModelMapper modelMapper =new ModelMapper();
    public static FeedbackDTO convertToDTO(Feedback feedback){
        FeedbackDTO feedbackDTO =new FeedbackDTO();
        BeanUtils.copyProperties(feedback,feedbackDTO);

        if (feedback.getCarBrands() != null) {
            feedbackDTO.setBrandId(feedback.getCarBrands().getBrandId());
        }

        if ( feedback.getCarModel() != null) {
            feedbackDTO.setModelId(feedback.getCarModel().getModelId());
        }

        if (feedback.getUsers() != null) {
            feedbackDTO.setUserId(feedback.getUsers().getUserId());
        }
        return feedbackDTO;
    }
    public static Feedback convertToEntity(FeedbackDTO feedbackDTO){
        Feedback feedback =new Feedback();
        BeanUtils.copyProperties(feedbackDTO,feedback);
        return feedback;
    }
}
