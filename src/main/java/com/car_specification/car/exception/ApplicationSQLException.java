package com.car_specification.car.exception;

public class ApplicationSQLException extends RuntimeException{
    public ApplicationSQLException(String message){
        super(message);
    }

    public ApplicationSQLException(String message, Throwable cause){
        super(message , cause);
    }
}
