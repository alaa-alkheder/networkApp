package com.javainuse.error;

import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
public class NotFoundException extends ApiBaseException{
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode(){
        return HttpStatus.NOT_FOUND;
    }
}
