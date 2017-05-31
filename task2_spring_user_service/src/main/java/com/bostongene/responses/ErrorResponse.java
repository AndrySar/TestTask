package com.bostongene.responses;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Andry on 29.05.17.
 */
public class ErrorResponse {

    @ApiModelProperty(notes = "Error message", required = true)
    private String message;

    public ErrorResponse(String message){ this.message = message; }

    @SuppressWarnings("unused")
    public String getMessage() {
        return message;
    }
}
