package com.prads.aquarium.controller;

import com.prads.aquarium.config.security.TokenService;
import com.prads.aquarium.controller.dto.AquariumDTO;
import com.prads.aquarium.controller.dto.AquariumDetailDTO;
import com.prads.aquarium.controller.dto.AuthDTO;
import com.prads.aquarium.controller.dto.UserDTO;
import com.prads.aquarium.controller.form.AquariumForm;
import com.prads.aquarium.controller.form.UpdateAquariumForm;
import com.prads.aquarium.controller.form.UpdateUserForm;
import com.prads.aquarium.controller.form.UserForm;
import com.prads.aquarium.exception.UserException;
import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.User;
import com.prads.aquarium.repository.AquariumRepository;
import com.prads.aquarium.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class SignController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/signup")
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity register(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {

        User newUser = userForm.toUser();

        Optional<User> user = userRepository.findByEmail(newUser.getEmail());

        if (user.isPresent()) {
            throw new UserException("Email already registered!");
        }

        userRepository.save(newUser);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(uri).body(UserDTO.toDto(newUser));
    }

    @PostMapping("/valid")
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<AuthDTO> isValid(@RequestHeader("Authorization") String bearerToken) {
        String token = recoverToken(bearerToken);
        Boolean isValid = tokenService.isValid(token);

        return ResponseEntity.ok(new AuthDTO(isValid));
    }

    @PutMapping("/update")
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<UserDTO> update(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid UpdateUserForm userForm) {
        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Optional<User> optional = userRepository.findById(userId);

        if (optional.isPresent()) {
            User user = userForm.updateUser(userId, userRepository);
            return ResponseEntity.ok(UserDTO.toDto(user));
        }

        return ResponseEntity.notFound().build();
    }

    private String recoverToken(String bearerToken) {

        if(bearerToken == null || bearerToken.isEmpty() || !bearerToken.startsWith("Bearer ")) {
            return null;
        }

        return bearerToken.substring(7, bearerToken.length());
    }
}
