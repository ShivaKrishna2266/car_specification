package com.car_specification.car.controller;

import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.dto.CarModelDTO;
import com.car_specification.car.dto.RoleDTO;
import com.car_specification.car.entity.ApiResponse;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.service.CarBrandService;
import com.car_specification.car.service.CarModelService;
import com.car_specification.car.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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



}
