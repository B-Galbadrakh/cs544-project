package cs544.group1.project.controller;

import cs544.group1.project.util.CustomError;
import cs544.group1.project.util.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class ProjectDefaultController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        CustomErrorResponse errorMessage = new CustomErrorResponse(400,"Validation Errors",errors);
        return ResponseEntity.status(400).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomError.class)
    public ResponseEntity<?> handleCustomExceptions(CustomError ex) {
        return ResponseEntity.status(ex.getCode()).body(new CustomErrorResponse(400,ex.getMessage(),ex.getDetails()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOtherExceptions(Exception ex) {
        Map<String,String> details = new HashMap();
        details.put("error",ex.getMessage());
        return ResponseEntity.status(500).body(new CustomErrorResponse(400,ex.getMessage(),details));
    }
}
