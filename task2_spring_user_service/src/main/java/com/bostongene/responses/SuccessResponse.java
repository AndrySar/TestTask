package com.bostongene.responses;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Andry on 29.05.17.
 */
public class SuccessResponse {

    @ApiModelProperty(notes = "User email", required = true)
    private String email;

    public SuccessResponse(String email) {
        this.email = email;
    }

    @SuppressWarnings("unused")
    public String getEmail() {
        return email;
    }
}
