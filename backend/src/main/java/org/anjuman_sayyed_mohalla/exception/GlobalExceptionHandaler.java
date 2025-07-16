package org.anjuman_sayyed_mohalla.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandaler {

    @ExceptionHandler(DuplicateRecordFoundException.class)
    public ResponseEntity<?> handleDuplicateRecordFoundException(DuplicateRecordFoundException exception){
        Map<String,Object> body = new HashMap<>();
        body.put("Timestamp",LocalDateTime.now());
        body.put("Error", HttpStatus.BAD_REQUEST);
        body.put("Message",exception.getMessage());

        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
        Map<String,Object> body = new HashMap<>();
        body.put("Timestamp",LocalDateTime.now());
        body.put("Error", HttpStatus.BAD_REQUEST);
        body.put("Message",exception.getMessage());

        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }
}
