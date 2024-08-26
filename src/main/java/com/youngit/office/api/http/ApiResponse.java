package com.youngit.office.api.http;

import lombok.Data;

@Data
public class ApiResponse<T> {
    T data;
    String message;
    int resultCode;
    int count;
    int status;

    public ApiResponse(T data)
    {
        this.data = data;
    }

    public ApiResponse(T data, int resultCode)
    {
        this.data = data;
        this.resultCode = resultCode;
    }

    public ApiResponse(T data, int resultCode, String message)
    {
        this.data = data;
        this.resultCode = resultCode;
        this.message = message;
    }
    public ApiResponse(T data, int resultCode, String message, int count)
    {
        this.data = data;
        this.resultCode = resultCode;
        this.message = message;
        this.count = count;
    }
}
