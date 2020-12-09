package com.prads.aquarium.controller.form;

import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.AquariumStatus;
import com.prads.aquarium.models.User;
import com.prads.aquarium.repository.AquariumRepository;
import com.sun.istack.Nullable;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class UpdateAquariumForm {

    @NotNull
    private int volume;
    @NotNull @NotEmpty @Length(min = 3)
    private String name;
    private float waterLevel;
    private float temperature;
    private boolean tempControllActive;
    private float setPointTemp;
    private float pH;
    private boolean phMonitActive;
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

    public Aquarium updatedAquarium(Long id, AquariumRepository aquariumRepository) {
        Aquarium aquarium = aquariumRepository.getOne(id);
        aquarium.setVolume(this.volume);
        aquarium.setName(this.name);
        aquarium.setWaterLevel(this.waterLevel);
        aquarium.setTemperature(this.temperature);
        aquarium.setTempControllActive(this.tempControllActive);
        aquarium.setSetPointTemp(this.setPointTemp);
        aquarium.setpH(this.pH);
        aquarium.setPhMonitActive(this.phMonitActive);
        aquarium.setStatus(this.status);

        return aquarium;
    }
}
