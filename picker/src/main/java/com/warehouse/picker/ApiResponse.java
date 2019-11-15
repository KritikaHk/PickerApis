package com.warehouse.picker;

import lombok.Data;

@Data
public class ApiResponse {

    private Integer code;
    private String message;
    private Object data;
}
