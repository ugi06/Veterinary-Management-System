package com.uur.vetmanagement.core.config.utill;

import com.uur.vetmanagement.core.config.exception.NotFoundException;
import com.uur.vetmanagement.core.result.Result;
import com.uur.vetmanagement.core.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<>(ResultData.notFoundException(e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData< List<String>>> handleValidationErrors(MethodArgumentNotValidException e){

        List<String> validatioinErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(ResultData.validationError(validatioinErrorList ), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({RuntimeException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleNotFoundException(RuntimeException ex) {
        return ResultData.notFoundException(ex.getMessage());
    }

}
