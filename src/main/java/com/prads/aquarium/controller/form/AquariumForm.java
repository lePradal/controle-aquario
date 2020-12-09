package com.prads.aquarium.controller.form;

import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.AquariumStatus;
import com.prads.aquarium.models.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

public class AquariumForm {

    @NotNull
    private int volume;
    @NotNull @NotEmpty @Length(min = 3)
    private String name;
    @Null
    private float waterLevel;
    @Null
    private float temperature;
    @Null
    private boolean tempControllActive;
    @Null
    private float setPointTemp;
    @Null
    private float pH;
    @Null
    private boolean phMonitActive;
    @Null
    @Enumerated(EnumType.STRING)
    private AquariumStatus status = AquariumStatus.OFFLINE;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(float waterLevel) {
        this.waterLevel = waterLevel;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public boolean isTempControllActive() {
        return tempControllActive;
    }

    public void setTempControllActive(boolean tempControllActive) {
        this.tempControllActive = tempControllActive;
    }

    public float getSetPointTemp() {
        return setPointTemp;
    }

    public void setSetPointTemp(float setPointTemp) {
        this.setPointTemp = setPointTemp;
    }

    public float getpH() {
        return pH;
    }

    public void setpH(float pH) {
        this.pH = pH;
    }

    public boolean isPhMonitActive() {
        return phMonitActive;
    }

    public void setPhMonitActive(boolean phMonitActive) {
        this.phMonitActive = phMonitActive;
    }

    public AquariumStatus getStatus() {
        return status;
    }

    public void setStatus(AquariumStatus status) {
        this.status = status;
    }

    public Aquarium toAquarium(User user) {
        return new Aquarium(name, volume, user);
    }
}
