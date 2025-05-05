package com.car_specification.car.controller;

import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.dto.CarModelDTO;
import com.car_specification.car.dto.EventRegisterDTO;
import com.car_specification.car.dto.EventsDTO;
import com.car_specification.car.dto.RoleDTO;
import com.car_specification.car.dto.UserRegistrationDTO;
import com.car_specification.car.entity.ApiResponse;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.service.CarBrandService;
import com.car_specification.car.service.CarModelService;
import com.car_specification.car.service.EventRegisterServices;
import com.car_specification.car.service.EventService;
import com.car_specification.car.service.RoleService;
import com.car_specification.car.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/data")
public class DataLoaderController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRegisterServices eventRegisterServices;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/getAllRoles")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getAllRoles(){
            ApiResponse<List<RoleDTO>> response =new ApiResponse<>();
           List<RoleDTO> roleDTO = roleService.getAllRoles();
        if (roleDTO != null){
            response.setStatus(200);
            response.setMessage("Fetch all records successfully!");
            response.setData(roleDTO);
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.setStatus(500);
            response.setMessage("Failed to Fetch!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


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

    @GetMapping("/getAllModelsByBrandId/{brandName}")
    public ResponseEntity<ApiResponse<List<CarModelDTO>>> getAllModelsByBrandId(@PathVariable String brandName) {
        ApiResponse<List<CarModelDTO>> response = new ApiResponse<>();
        List<CarModelDTO> carModels = carModelService.getAllModelsByBrandId(brandName);
        if (carModels != null) {
            response.setStatus(200);
            response.setMessage("Successfully get a CarModel By car brandId !");
            response.setData(carModels);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMessage("Failed to get a CarModelByCarBrandId!");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    ===================Car Model==============================//


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


    @GetMapping("/getCarModelById/{modelId}")
    public ResponseEntity<ApiResponse<CarModelDTO>> getCarModelById(@PathVariable Integer modelId) {
        ApiResponse<CarModelDTO> response = new ApiResponse<>();
        CarModelDTO carModelDTO = carModelService.getCarModelById(modelId);
        if (carModelDTO != null) {
            response.setStatus(200);
            response.setMessage("Fetch Record Successfully");
            response.setData(carModelDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMessage("Record Not Fetched");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//==================================EVENTS============================================//


    @GetMapping("/getAllEvents")
    public ResponseEntity<ApiResponse<List<EventsDTO>>> getAllEvents() {
        ApiResponse<List<EventsDTO>> response = new ApiResponse<>();
        try {
            List<EventsDTO> eventsDTOS = eventService.getAllEvents();
            if (eventsDTOS != null) {
                response.setStatus(200);
                response.setMessage("Fetched all events successfully!");
                response.setData(eventsDTOS);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to fetch all events!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to fetch events!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getEventById/{eventId}")
    public ResponseEntity<ApiResponse<EventsDTO>> getEventById(@PathVariable Long eventId) {
        ApiResponse<EventsDTO> response = new ApiResponse<>();
        EventsDTO eventsDTO = eventService.getEventById(eventId);
        if (eventsDTO != null) {
            response.setStatus(200);
            response.setMessage("Fetch Record Successfully");
            response.setData(eventsDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(500);
            response.setMessage("Record Not Fetched");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/addEvents")
    public ResponseEntity<ApiResponse<EventsDTO>> addEvents(@RequestBody EventsDTO eventsDTO) {
        ApiResponse<EventsDTO> response = new ApiResponse<>();
        try {
            EventsDTO addEvents = eventService.createEvent(eventsDTO);
            if (addEvents != null) {
                response.setStatus(200);
                response.setMessage("Successfully added a carModel!");
                response.setData(addEvents);
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


    @PutMapping("/updateEvent/{eventId}")
    public ResponseEntity<ApiResponse<EventsDTO>> updateEvent(@PathVariable Long eventId, @RequestBody EventsDTO eventsDTO) {
        ApiResponse<EventsDTO> response = new ApiResponse<>();
        try {
            EventsDTO updateEvents = eventService.updateEvent(eventId, eventsDTO);
            if (updateEvents != null) {
                response.setStatus(200);
                response.setMessage("Successfully updated a Event!");
                response.setData(updateEvents);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to update a Event!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to update a Event!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteEvent/{eventId}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable Long eventId) {
        ApiResponse<Void> response = new ApiResponse<>();
        try {
            eventService.deleteEventById(eventId);
            if (eventId != null) {
                response.setStatus(200);
                response.setMessage("Successfully deleted a event!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to delete a event!");
                response.setData(null);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to delete a event!" + ae.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    =========================EVENT REGISTER==========================//




    @GetMapping("/getAllEventRegisters")
    public ResponseEntity<ApiResponse<List<EventRegisterDTO>>>getAllEventRegisters(){
        ApiResponse<List<EventRegisterDTO>> response = new ApiResponse<>();
            List<EventRegisterDTO> eventsDTOS = eventRegisterServices.getAllEventRegisters();
            if (eventsDTOS != null) {
                response.setStatus(200);
                response.setMessage("Fetched all events successfully!");
                response.setData(eventsDTOS);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to fetch all events!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }


    @PostMapping("/addEventRegister")
    public ResponseEntity<ApiResponse<EventRegisterDTO>> addEventRegister(@RequestBody EventRegisterDTO eventRegisterDTO) {

        ApiResponse<EventRegisterDTO> response = new ApiResponse<>();
        try {
            EventRegisterDTO addEventRegister = eventRegisterServices.createEventRegister(eventRegisterDTO);
            if (addEventRegister != null) {
                response.setStatus(200);
                response.setMessage("Successfully added a EventRegisters!");
                response.setData(addEventRegister);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatus(500);
                response.setMessage("Failed to add a EventRegisters!");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (ApplicationBusinessException ae) {
            response.setStatus(500);
            response.setMessage("Unable to add a EventRegisters!" + ae.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUsersByEventId/{eventId}")
    public ResponseEntity<Map<String, Object>> getUsersByEventId(@PathVariable Long eventId) {
        // Get the list of registered users for the event
        List<UserRegistrationDTO> users = userDetailsService.getUsersByEventId(eventId);

        // Prepare the response
        Map<String, Object> response = new HashMap<>();
        response.put("data", users);  // Add users list to the response body

        return ResponseEntity.ok(response);  // Return the response with status 200 OK
    }
}
