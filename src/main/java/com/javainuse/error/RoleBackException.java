package com.javainuse.error;

import org.springframework.http.HttpStatus;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
public class RoleBackException extends ApiBaseException{
    public RoleBackException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode(){
        return HttpStatus.EXPECTATION_FAILED;
    }
}
