package com.example.EmployeeServer.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> myExpHandler(IllegalArgumentException ie){

        return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<MyErrorDetails> methodArgumentNotValidHandler(MethodArgumentNotValidException manve, WebRequest request) {

        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setMessage(manve.getMessage());
        errorDetails.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception e, WebRequest req){

        MyErrorDetails errorDetails = new MyErrorDetails(LocalDateTime.now(),e.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<MyErrorDetails> myNotFoundHandler(NoHandlerFoundException nfe,WebRequest req)  {

        MyErrorDetails err=new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<MyErrorDetails> categoryExceptionHandler(EmployeeException employeeException, WebRequest request){

        MyErrorDetails errorDetails = new MyErrorDetails();
        errorDetails.setDetails(request.getDescription(false));
        errorDetails.setMessage(employeeException.getMessage());
        errorDetails.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);

    }
}
