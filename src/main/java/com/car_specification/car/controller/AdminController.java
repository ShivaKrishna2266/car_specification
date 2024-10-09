package com.car_specification.car.controller;

import com.car_specification.car.dto.AppointmentDTO;
import com.car_specification.car.dto.CarColourDTO;
import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.dto.CarModelDTO;
import com.car_specification.car.dto.FeedbackDTO;
import com.car_specification.car.entity.ApiResponse;
import com.car_specification.car.entity.CarColour;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.service.AppointmentService;
import com.car_specification.car.service.CarColourService;
import com.car_specification.car.service.CarBrandService;
import com.car_specification.car.service.CarModelService;
import com.car_specification.car.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private CarColourService carColourService;

    //    ================================CarModels==================================

    @Autowired
    private CarBrandService carBrandService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllCarModels")
    public ResponseEntity<ApiResponse<List<CarModelDTO>>> getAllCarModels() {
        ApiResponse<List<CarModelDTO>> response = new ApiResponse<>();
        try {
            List<CarModelDTO> carModelDTOS = carModelService.getAllCarModels();
            if (carModelDTOS != null) {
                response.setStatus(200);
                response.setMessage("Fetched all carModels successfully!");
                response.setData(carModelDTOS);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to fetch all carModels!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to fetch carModels!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN)")
    @PostMapping("/addCarModel")
    public ResponseEntity<ApiResponse<CarModelDTO>> addCarModel(@RequestBody CarModelDTO carModelDTO) {
        ApiResponse<CarModelDTO> response = new ApiResponse<>();
        try {
            CarModelDTO addCarModelDTO = carModelService.createCarModel(carModelDTO);
            if (addCarModelDTO != null) {
                response.setStatus(200);
                response.setMessage("Successfully added a carModel!");
                response.setData(addCarModelDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to add a carModel!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to add a carModel!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN)")
    @PutMapping("/updateCarModel/{carModelId}")
    public ResponseEntity<ApiResponse<CarModelDTO>> updateCarModel(@PathVariable Integer carModelId, @RequestBody CarModelDTO carModelDTO) {
        ApiResponse<CarModelDTO> response = new ApiResponse<>();
        try {
            CarModelDTO updateCarModelDTO = carModelService.updateCarModel(carModelId, carModelDTO);
            if (updateCarModelDTO != null) {
                response.setStatus(200);
                response.setMessage("Successfully updated a carModel!");
                response.setData(updateCarModelDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to update a carModel!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to update a carModel!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN)")
    @DeleteMapping("/deleteCarModel/{carModelId}")
    public ResponseEntity<ApiResponse<Void>> deleteCarModel(@PathVariable Integer carModelId) {
        ApiResponse<Void> response = new ApiResponse<>();
        try {
            carModelService.deleteCarModelById(carModelId);
            if (carModelId != null) {
                response.setStatus(200);
                response.setMessage("Successfully deleted a carModel!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to delete a carModel!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to delete a carModel!" + ae.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //    ================================Appointments==================================
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllAppointments")
    public ResponseEntity<ApiResponse<List<AppointmentDTO>>> getAllAppointments() {
        ApiResponse<List<AppointmentDTO>> response = new ApiResponse<>();
        try {
            List<AppointmentDTO> appointmentDTOS = appointmentService.getAllAppointments();
            if (appointmentDTOS != null) {
                response.setStatus(200);
                response.setMessage("Fetched all appointments successfully!");
                response.setData(appointmentDTOS);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to fetch all appointments!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to fetch appointments!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN)")
    @PostMapping("/addAppointment")
    public ResponseEntity<ApiResponse<AppointmentDTO>> addCarModel(@RequestBody AppointmentDTO appointmentDTO) {
        ApiResponse<AppointmentDTO> response = new ApiResponse<>();
        try {
            AppointmentDTO addAppointmentDTO = appointmentService.createAppointment(appointmentDTO);
            if (addAppointmentDTO != null) {
                response.setStatus(200);
                response.setMessage("Successfully added an appointment!");
                response.setData(addAppointmentDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to add an appointment!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to add an appointment!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN)")
    @PutMapping("/updateAppointment/{appointmentId}")
    public ResponseEntity<ApiResponse<AppointmentDTO>> updateAppointment(@PathVariable Integer appointmentId, @RequestBody AppointmentDTO appointmentDTO) {
        ApiResponse<AppointmentDTO> response = new ApiResponse<>();
        try {
            AppointmentDTO updateAppointmentDTO = appointmentService.updateAppointment(appointmentId, appointmentDTO);
            if (updateAppointmentDTO != null) {
                response.setStatus(200);
                response.setMessage("Successfully updated an appointment!");
                response.setData(updateAppointmentDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to update an appointment!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to update an appointment!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN)")
    @DeleteMapping("/deleteAppointment/{appointmentId}")
    public ResponseEntity<ApiResponse<Void>> deleteAppointment(@PathVariable Integer appointmentId) {
        ApiResponse<Void> response = new ApiResponse<>();
        try {
            carModelService.deleteCarModelById(appointmentId);
            if (appointmentId != null) {
                response.setStatus(200);
                response.setMessage("Successfully deleted an appointment!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to delete an appointment!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to delete an appointment!" + ae.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    ================================FeedBAck==================================


    @GetMapping("/getAllFeedbacks")
    public ResponseEntity<ApiResponse<List<FeedbackDTO>>> getAllFeedbacks(){
        ApiResponse<List<FeedbackDTO>> response = new ApiResponse<>();
        List<FeedbackDTO> feedbackDTOS = feedbackService.getAllFeedbacks();
        if (feedbackDTOS != null){
            response.setStatus(200);
            response.setMessage("Fetch all records successfully!");
            response.setData(feedbackDTOS);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setStatus(500);
            response.setMessage("Failed to Fetch!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{getFeedBackById}")
    public ResponseEntity<ApiResponse<FeedbackDTO>> getFeedBackById(@PathVariable Integer feedbackId){
        ApiResponse<FeedbackDTO> response = new ApiResponse<>();
        FeedbackDTO feedbackDTOS = feedbackService.getFeedbackById(feedbackId);
        if(feedbackDTOS != null){
            response.setStatus(200);
            response.setMessage("Fetch Record Successfully");
            response.setData(feedbackDTOS);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setStatus(500);
            response.setMessage("Record Not Fetched");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createFeedback")
    public ResponseEntity<ApiResponse<FeedbackDTO>> createFeedback(@RequestBody FeedbackDTO feedbackDTO){
        ApiResponse<FeedbackDTO> response = new ApiResponse<>();
        FeedbackDTO feedbackDTO1 = feedbackService.createFeedback(feedbackDTO);
        if(feedbackDTO1 != null){
            response.setStatus(200);
            response.setMessage("Created a FeedBack successfully!");
            response.setData(feedbackDTO1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setStatus(500);
            response.setMessage("Failed to create a FeedBack!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateFeedback/{feedBackId}")
    public ResponseEntity<ApiResponse<FeedbackDTO>> updateFeedback(@PathVariable Integer feedBackId,
                                                                   @RequestBody FeedbackDTO feedbackDTO) {
        ApiResponse<FeedbackDTO> response = new ApiResponse<>();
        FeedbackDTO feedbackDTO1 = feedbackService.updateFeedback(feedBackId, feedbackDTO);
        if (feedbackDTO1 != null) {
            response.setStatus(200);
            response.setMessage("Update a FeedBack successfully!");
            response.setData(feedbackDTO1);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMessage("Failed to update a FeedBack!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    @DeleteMapping("/deleteFeedback/{feedBackId}")
//    public ResponseEntity<ApiResponse<FeedbackDTO>> deleteFeedback(@PathVariable Integer feedBackId){
//        ApiResponse<FeedbackDTO> response = new ApiResponse<>();
//        FeedbackDTO feedbackDTO1 = feedbackService.deleteFeedbackById(feedBackId);
//        if(feedbackDTO1 != null){
//            response.setStatus(200);
//            response.setMessage("Update a FeedBack successfully!");
//            response.setData(feedbackDTO1);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }else {
//            response.setStatus(500);
//            response.setMessage("Failed to update a FeedBack!");
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @DeleteMapping("/deleteFeedBack/{feedBackId}")
    public ResponseEntity<ApiResponse<Void>> deleteFeedBack(@PathVariable Integer feedBackId) {
        ApiResponse<Void> response = new ApiResponse<>();
        try {
            feedbackService.deleteFeedbackById(feedBackId);
            if (feedBackId != null) {
                response.setStatus(200);
                response.setMessage("Successfully deleted an feedBack!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to delete an feedBack!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to delete an feedBack!" + ae.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//        ======================CarBrand=============================

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllCarBrand")
    public ResponseEntity<ApiResponse<List<CarBrandDTO>>> getAllCarBrand() {
        ApiResponse<List<CarBrandDTO>> response = new ApiResponse<>();
        try {
            List<CarBrandDTO> carBrandDTOS = carBrandService.getAllCarBrands();
            if (carBrandDTOS != null) {
                response.setStatus(200);
                response.setMessage("Fetched all carBrand successfully!");
                response.setData(carBrandDTOS);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to fetch all carBrand!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to fetch carBrand!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{getCarBrandId}")
    public ResponseEntity<ApiResponse<CarBrandDTO>> getCarBrandId(@PathVariable Integer carBrandId) {
        ApiResponse<CarBrandDTO> response = new ApiResponse<>();
        CarBrandDTO carBrandDTO = carBrandService.getCarBrandById(carBrandId);
        if (carBrandDTO != null) {
            response.setStatus(200);
            response.setMessage("Fetch Record Successfully");
            response.setData(carBrandDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMessage("Record Not Fetched");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('ADMIN)")
    @PostMapping("/addCarBrand")
    public ResponseEntity<ApiResponse<CarBrandDTO>> addCarBrand(@RequestBody CarBrandDTO carBrandDTO) {
        ApiResponse<CarBrandDTO> response = new ApiResponse<>();
        try {
            CarBrandDTO addCarBrandDTO = carBrandService.createCarBrand(carBrandDTO);
            if (addCarBrandDTO != null) {
                response.setStatus(200);
                response.setMessage("Successfully added a carBrand!");
                response.setData(addCarBrandDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to add a carBrand!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to add a carBrand!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN)")
    @PutMapping("/updateCarBrand/{carBrandId}")
    public ResponseEntity<ApiResponse<CarBrandDTO>> updateCarBrand(@PathVariable Integer carBrandId, @RequestBody CarBrandDTO carBrandDTO) {
        ApiResponse<CarBrandDTO> response = new ApiResponse<>();
        try {
            CarBrandDTO updateCarBrandDTO = carBrandService.updateCarBrand(carBrandId, carBrandDTO);
            if (updateCarBrandDTO != null) {
                response.setStatus(200);
                response.setMessage("Successfully updated a carBrand!");
                response.setData(updateCarBrandDTO);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to update a CarBrand!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to update a CarBrand!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN)")
    @DeleteMapping("/deleteCarBrand/{carBrandId}")
    public ResponseEntity<ApiResponse<Void>> deleteCarBrand(@PathVariable Integer carBrandId) {
        ApiResponse<Void> response = new ApiResponse<>();
        try {
            carBrandService.deleteCarBrandById(carBrandId);
            if (carBrandId != null) {
                response.setStatus(200);
                response.setMessage("Successfully deleted an carBrand!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to delete an carBrand!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to delete an carBrand!" + ae.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

