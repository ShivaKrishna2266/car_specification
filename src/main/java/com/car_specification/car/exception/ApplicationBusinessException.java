package com.car_specification.car.exception;

public class ApplicationBusinessException extends RuntimeException{

    public ApplicationBusinessException(String message){
        super(message);
    }


    public ApplicationBusinessException(String message, Throwable cause){
        super(message, cause);
    }
}
