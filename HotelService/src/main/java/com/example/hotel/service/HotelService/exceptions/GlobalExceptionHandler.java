package com.example.hotel.service.HotelService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // This class can be used to handle exceptions globally for the HotelService application.
    // You can define methods here to handle specific exceptions and return appropriate responses.

    // Example:
    // @ExceptionHandler(ResourceNotFoundException.class)
    // public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    // }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {
        //instead of APIResponse
       Map map=new HashMap();
         map.put("message",ex.getMessage());
         map.put("success",false);
         map.put("status", HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }
}
