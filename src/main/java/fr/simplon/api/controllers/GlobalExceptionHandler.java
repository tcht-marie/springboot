package fr.simplon.api.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setType(exception.getClass().getName());
        errorDetails.setMessage(exception.getLocalizedMessage());

        return new ResponseEntity<>(errorDetails, resolveAnnotationResponseStatus(exception));
    }

    private HttpStatus resolveAnnotationResponseStatus(Exception exception) {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
        return responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}

@Getter @Setter
class ErrorDetails {

    String type;
    String message;
}