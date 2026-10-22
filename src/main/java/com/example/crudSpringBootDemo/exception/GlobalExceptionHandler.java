package com.example.crudSpringBootDemo.exception;

import com.example.crudSpringBootDemo.dto.ExceptionResponseDto;
import com.example.crudSpringBootDemo.dto.ValidationExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletRequest request){
       Map<String,String> map=new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach((fieldError)->map.put(fieldError.getField(),fieldError.getDefaultMessage()));
        ValidationExceptionResponseDto dto=new ValidationExceptionResponseDto(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage(),request.getRequestURI(),map);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(dto);

    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateResourceException(DuplicateResourceException ex, HttpServletRequest request){
       ExceptionResponseDto dto=new ExceptionResponseDto(LocalDateTime.now(),HttpStatus.CONFLICT.value(),request.getRequestURI(),HttpStatus.CONFLICT.getReasonPhrase(),ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(dto);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,HttpServletRequest request){
        ExceptionResponseDto dto=new ExceptionResponseDto(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),request.getRequestURI(),HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(dto);

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleRuntimeError(RuntimeException ex,HttpServletRequest request){
        ExceptionResponseDto dto=new ExceptionResponseDto(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(),request.getRequestURI(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(dto);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleGenericException(Exception ex,HttpServletRequest request){
        ExceptionResponseDto dto=new ExceptionResponseDto(LocalDateTime.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(),request.getRequestURI(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(dto);
    }

}
