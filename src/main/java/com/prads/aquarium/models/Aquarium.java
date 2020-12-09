package com.prads.aquarium.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Aquarium {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int volume;
	private float waterLevel;
	private boolean controlActive;
	private float temperature;
	private boolean tempControlActive;
	private float setPointTemp;
	private float pH;
	private boolean phMonitActive;
	private LocalDateTime creationDate = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private AquariumStatus status = AquariumStatus.OFFLINE;
	@ManyToOne
	private User user;

	public Aquarium() {}

	public Aquarium(String name, int volume, User user) {
		this.name = name;
		this.volume = volume;
		this.user = user;
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
		Aquarium other = (Aquarium) obj;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public boolean getControlActive() {
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

	public boolean getTempControlActive() {
		return tempControlActive;
	}

	public void setTempControlActive(boolean active) {
		this.tempControlActive = active;
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

	public boolean getPhMonitActive() {
		return phMonitActive;
	}

	public void setPhMonitActive(boolean active) {
		this.phMonitActive = active;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public AquariumStatus getStatus() {
		return status;
	}

	public void setStatus(AquariumStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
