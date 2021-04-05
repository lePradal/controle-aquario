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
    private String imageUrl;
    @NotNull
    private int volume;
    private boolean controlActive;
    private float temperature;
    private float setPointTemp;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
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

    public float getSetPointTemp() {
        return setPointTemp;
    }

    public void setSetPointTemp(float setPointTemp) {
        this.setPointTemp = setPointTemp;
    }

    public AquariumStatus getStatus() {
        return status;
    }

    public void setStatus(AquariumStatus status) {
        this.status = status;
    }

    public Aquarium updatedAquarium(Long id, AquariumRepository aquariumRepository) {
        Aquarium aquarium = aquariumRepository.getOne(id);
        aquarium.setName(this.name);
        aquarium.setDescription(this.description);
        aquarium.setImageUrl(this.imageUrl);
        aquarium.setVolume(this.volume);
        aquarium.setControlActive(this.controlActive);
        aquarium.setTemperature(this.temperature);
        aquarium.setSetPointTemp(this.setPointTemp);
        aquarium.setStatus(this.status);

        return aquarium;
    }
}
