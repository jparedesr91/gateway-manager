package com.musalasoft.practicaltask.GatewayManager.aop;

import com.musalasoft.practicaltask.GatewayManager.dto.ErrorDetails;
import com.musalasoft.practicaltask.GatewayManager.exceptions.DataAlreadyExistException;
import com.musalasoft.practicaltask.GatewayManager.exceptions.DataNotFoundException;
import com.musalasoft.practicaltask.GatewayManager.exceptions.InvalidDataException;
import com.musalasoft.practicaltask.GatewayManager.exceptions.PeripheralSizeException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(DataNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "Server internal error: " + ex.getMessage());
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistException(DataAlreadyExistException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.CONFLICT, ex.getLocalizedMessage(), "Server internal error: " + ex.getMessage());
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }

    @ExceptionHandler(InvalidDataException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistException(InvalidDataException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Server internal error: " + ex.getMessage());

        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());

    }

    @ExceptionHandler(PeripheralSizeException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistException(PeripheralSizeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Server internal error: " + ex.getMessage());

        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleCommonException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Server internal error: " + ex.getMessage());
        return new ResponseEntity<Object>(
                errorDetails, new HttpHeaders(), errorDetails.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errorList);
        return handleExceptionInternal(ex, errorDetails, headers, errorDetails.getStatus(), request);
    }
}
