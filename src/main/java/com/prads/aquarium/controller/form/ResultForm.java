package com.prads.aquarium.controller.form;

import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.Result;
import com.prads.aquarium.models.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ResultForm {

    @NotNull
    private Long userId;

    @NotNull
    private Long aquariumId;

    @NotNull
    private float weatherTemperature;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAquariumId() {
        return aquariumId;
    }

    public void setAquariumId(Long aquariumId) {
        this.aquariumId = aquariumId;
    }

    public float getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(float weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public Result toResult(User user, Aquarium aquarium) {
        return new Result(user, aquarium, weatherTemperature);
    }
}
