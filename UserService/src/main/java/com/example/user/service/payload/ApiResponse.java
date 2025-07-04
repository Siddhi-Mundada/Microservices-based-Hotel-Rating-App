package com.example.user.service.payload;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder // to use builder pattern for creating ApiResponse objects
public class ApiResponse {
    private String message;
    private boolean success;
//    private HttpStatus status;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public HttpStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(HttpStatus status) {
//        this.status = status;
//    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
