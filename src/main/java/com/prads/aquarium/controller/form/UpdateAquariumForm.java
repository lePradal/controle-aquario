package com.prads.aquarium.controller.form;

import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.AquariumStatus;
import com.prads.aquarium.models.User;
import com.prads.aquarium.repository.AquariumRepository;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateAquariumForm {

    @NotNull @NotEmpty @Length(min = 3)
    private String name;
    private String description;
    @NotNull
    private int volume;
    private float waterLevel;
    private boolean controlActive;
    private float temperature;
    private boolean tempControlActive;
    private float setPointTemp;
    private float pH;
    private boolean phMonitActive;
    @Enumerated(EnumType.STRING)
    private AquariumStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public float getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(float waterLevel) {
        this.waterLevel = waterLevel;
    }

    public boolean isControlActive() {
        return controlActive;
    }

    public void setControlActive(boolean controlActive) {
        this.controlActive = controlActive;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public boolean isTempControlActive() {
        return tempControlActive;
    }

    public void setTempControlActive(boolean tempControlActive) {
        this.tempControlActive = tempControlActive;
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

    public Aquarium updatedAquarium(Long id, AquariumRepository aquariumRepository) {
        Aquarium aquarium = aquariumRepository.getOne(id);
        aquarium.setName(this.name);
        aquarium.setDescription(this.description);
        aquarium.setVolume(this.volume);
        aquarium.setWaterLevel(this.waterLevel);
        aquarium.setControlActive(this.controlActive);
        aquarium.setTemperature(this.temperature);
        aquarium.setTempControlActive(this.tempControlActive);
        aquarium.setSetPointTemp(this.setPointTemp);
        aquarium.setpH(this.pH);
        aquarium.setPhMonitActive(this.phMonitActive);
        aquarium.setStatus(this.status);

        return aquarium;
    }
}
