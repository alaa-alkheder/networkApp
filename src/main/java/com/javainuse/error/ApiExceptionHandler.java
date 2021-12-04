package com.javainuse.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ApiBaseException.class)
    public ResponseEntity<ErrorDetails> hundleApiException(ApiBaseException e, WebRequest webRequest){
        ErrorDetails details=new ErrorDetails(e.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(details,e.getStatusCode());
    }

}
