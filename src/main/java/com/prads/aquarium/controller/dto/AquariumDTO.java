package com.prads.aquarium.controller.dto;

import com.prads.aquarium.models.Aquarium;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AquariumDTO {
    private Long id;
    private String name;
    private LocalDateTime creationDate;

    public AquariumDTO(Aquarium aquarium) {
        this.id = aquarium.getId();
        this.name = aquarium.getName();
        this.creationDate = aquarium.getCreationDate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public LocalDateTime getCreationDate() {
        return creationDate;
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
