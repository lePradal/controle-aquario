package com.prads.aquarium.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="tb_aquarium")
public class Aquarium implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String imageUrl;
	private int volume;
	private boolean controlActive;
	private float temperature;
	private float setPointTemp;
	private LocalDateTime creationDate = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	private AquariumStatus status = AquariumStatus.OFFLINE;
	@ManyToOne
	private User user;

	public Aquarium() {}

	public Aquarium(String name, int volume, String description, String imageUrl, User user) {
		this.name = name;
		this.volume = volume;
		this.description = description;
		this.imageUrl = imageUrl;
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

	public float getSetPointTemp() {
		return setPointTemp;
	}

	public void setSetPointTemp(float setPointTemp) {
		this.setPointTemp = setPointTemp;
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
