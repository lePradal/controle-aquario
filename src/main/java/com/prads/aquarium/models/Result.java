package com.prads.aquarium.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    private User user;

    @ManyToOne
    private Aquarium aquarium;

    private float weatherTemperature;
    private float aquariumTemperature;

    public Result() {}

    public Result(User user, Aquarium aquarium, float weatherTemperature) {
        this.user = user;
        this.aquarium = aquarium;
        this.weatherTemperature = weatherTemperature;
        this.aquariumTemperature = aquarium.getTemperature();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Result other = (Result) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Aquarium getAquarium() {
        return aquarium;
    }

    public void setAquarium(Aquarium aquarium) {
        this.aquarium = aquarium;
    }

    public float getWeatherTemperature() {
        return weatherTemperature;
    }

    public void setWeatherTemperature(float weatherTemperature) {
        this.weatherTemperature = weatherTemperature;
    }

    public float getAquariumTemperature() {
        return aquariumTemperature;
    }

    public void setAquariumTemperature(float aquariumTemperature) {
        this.aquariumTemperature = aquariumTemperature;
    }
}
