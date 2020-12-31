package com.prads.aquarium.controller.form;

import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.AquariumStatus;
import com.prads.aquarium.models.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class AquariumForm {

    @NotNull
    private int volume;
    @NotNull @NotEmpty @Length(min = 3)
    private String name;

    private String description;

    private String imageBase64;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public Aquarium toAquarium(User user) {
        return new Aquarium(name, volume, user);
    }
}
