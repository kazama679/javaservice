//package com.ra.ss8.advice_controller;
//
//import com.ra.ss8.model.dto.resonse.DataErrorResponse;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class APIRestControllerAdvice {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public DataErrorResponse<Map<String, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getFieldError().forEach(fieldError -> {
//            errors.put(errors.getField(), fieldError.getDefaultMessage())}
//        );
//    }
//}