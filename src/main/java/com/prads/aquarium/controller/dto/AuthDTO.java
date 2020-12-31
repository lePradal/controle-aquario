package com.prads.aquarium.controller.dto;

public class AuthDTO {
    private Boolean valid;

    public AuthDTO(Boolean valid) {
        this.valid = valid;
    }

    public Boolean getValid() {
        return valid;
    }
}
