package com.prads.aquarium.controller.dto;

import com.prads.aquarium.models.Aquarium;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AquariumDTO {
    private Long id;
    private String name;
    private int volume;
    private LocalDateTime creationDate;
    private String userName;

    public AquariumDTO(Aquarium aquarium) {
        this.id = aquarium.getId();
        this.volume = aquarium.getVolume();
        this.name = aquarium.getName();
        this.creationDate = aquarium.getCreationDate();
        this.userName = aquarium.getUser().getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getUserName() {
        return userName;
    }
    public static AquariumDTO toDto(Aquarium aquarium) {
        return new AquariumDTO(aquarium);
    }

    public static List<AquariumDTO> toDto(List<Aquarium> aquariums) {
        return aquariums.stream().map(AquariumDTO::new).collect(Collectors.toList());
    }

    public static Page<AquariumDTO> toDto(Page<Aquarium> aquarium) {
        return aquarium.map(AquariumDTO::new);
    }
}
