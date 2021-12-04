package com.javainuse.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
public class ErrorDetails {
    private String massage;
    private String url;
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "dd-MM-yy hh:mm:ss")
    private Date timeStamp;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ErrorDetails() {
        timeStamp=new Date();
    }

    public ErrorDetails(String massage, String url) {
        this();
        this.massage = massage;
        this.url = url;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
