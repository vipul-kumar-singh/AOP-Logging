package com.vkstech.AOPLogging.dto;

import lombok.Data;

@Data
public class ResponseObject {

    private Long timestamp;
    private Object data;
    private String message;

    public ResponseObject(String message, Object data) {
        this.timestamp = System.currentTimeMillis();
        this.message = message;
        this.data = data;
    }
}
