package com.prads.aquarium.controller.dto;

import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.AquariumStatus;
import com.prads.aquarium.models.User;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AquariumDetailDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private int volume;
    private float temperature;
    private boolean controlActive;
    private float setPointTemp;
    private LocalDateTime creationDate;
    private AquariumStatus status;

    public AquariumDetailDTO(Aquarium aquarium) {
        this.id = aquarium.getId();
        this.name = aquarium.getName();
        this.description = aquarium.getDescription();
        this.imageUrl = aquarium.getImageUrl();
        this.volume = aquarium.getVolume();
        this.temperature = aquarium.getTemperature();
        this.controlActive = aquarium.getControlActive();
        this.setPointTemp = aquarium.getSetPointTemp();
        this.creationDate = aquarium.getCreationDate();
        this.status = aquarium.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getVolume() {
        return volume;
    }

    public float getTemperature() {
        return temperature;
    }

    public boolean isControlActive() {
        return controlActive;
    }

    public float getSetPointTemp() {
        return setPointTemp;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public AquariumStatus getStatus() {
        return status;
    }

    public static AquariumDetailDTO toDto(Aquarium aquarium) {
        return new AquariumDetailDTO(aquarium);
    }

    public static List<AquariumDetailDTO> toDto(List<Aquarium> aquariums) {
        return aquariums.stream().map(AquariumDetailDTO::new).collect(Collectors.toList());
    }

    public static Page<AquariumDetailDTO> toDto(Page<Aquarium> aquarium) {
        return aquarium.map(AquariumDetailDTO::new);
    }
}
