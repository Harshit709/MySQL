package com.mysql.in.MySQL.dto;

import lombok.Builder;

@Builder
public record BaseResponse<T>( String resultMessage, T data) {

    public BaseResponse(Integer resultCode, String resultMessage) {
        this( resultMessage, null);
    }



    public BaseResponse(T data,String resultMessage) {
        this(resultMessage, data);
    }

    public BaseResponse(String resultMessage) {
        this(resultMessage,null);
    }
}


