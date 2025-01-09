package com.car_specification.car.controller;

import com.car_specification.car.dto.CarBrandDTO;
import com.car_specification.car.dto.RoleDTO;
import com.car_specification.car.entity.ApiResponse;
import com.car_specification.car.exception.ApplicationBusinessException;
import com.car_specification.car.service.CarBrandService;
import com.car_specification.car.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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


}
