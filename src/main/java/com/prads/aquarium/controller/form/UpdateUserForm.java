package com.prads.aquarium.controller.form;

import com.prads.aquarium.models.User;
import com.prads.aquarium.repository.UserRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateUserForm {

    @NotNull @NotEmpty @Length(min = 6)
    private String name;

    @NotNull @NotEmpty
    private String surname;

    @NotNull @NotEmpty @Length(min = 6)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User updateUser(Long id, UserRepository userRepository) {
        User user = userRepository.getOne(id);
        user.setName(this.name);
        user.setSurname(this.surname);
        user.setEmail(this.email);

        return user;
    }
}
