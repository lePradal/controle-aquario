package com.prads.aquarium.controller;

import com.prads.aquarium.config.security.AuthenticationService;
import com.prads.aquarium.config.security.TokenService;
import com.prads.aquarium.controller.dto.AquariumDTO;
import com.prads.aquarium.controller.form.AquariumForm;
import com.prads.aquarium.controller.form.UpdateAquariumForm;
import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.User;
import com.prads.aquarium.repository.AquariumRepository;
import com.prads.aquarium.repository.UserRepository;
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

import java.net.URI;
import java.text.ParseException;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/aquariums")
public class AquariumsController {

    @Autowired
    private AquariumRepository aquariumRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @Cacheable(value = "aquariumsList")
    public ResponseEntity list(@RequestHeader("Authorization") String bearerToken, @PageableDefault(page = 0, size = 5, sort = "creationDate", direction = Direction.DESC) Pageable pageable
    ) throws ParseException {

        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Page<Aquarium> aquariumPage;

        aquariumPage = aquariumRepository.findByUserId(userId, pageable);

        Page ret = AquariumDTO.toDto(aquariumPage);

        if (ret.getTotalElements() > 0) {
            return ResponseEntity.ok(ret);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AquariumDTO> detail(@RequestHeader("Authorization") String bearerToken, @PathVariable Long id) {
        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Optional<Aquarium> optional = aquariumRepository.findByIdAndUserId(id, userId);

        if (!optional.isPresent()) {
            throw new UsernameNotFoundException("Invalid Aquarium!");
        }

        return optional
                .map(aquarium -> ResponseEntity.ok(AquariumDTO.toDto(aquarium)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "aquariumsList", allEntries = true)
    public ResponseEntity<AquariumDTO> register(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid AquariumForm aquariumForm, UriComponentsBuilder uriBuilder) {
        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Invalid User!");
        }

        Aquarium aquarium = aquariumForm.toAquarium(user.get());
        aquariumRepository.save(aquarium);

        URI uri = uriBuilder.path("/aquariums/{id}").buildAndExpand(aquarium.getId()).toUri();

        return ResponseEntity.created(uri).body(AquariumDTO.toDto(aquarium));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "aquariumsList", allEntries = true)
    public ResponseEntity<AquariumDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateAquariumForm aquariumForm) {
        Optional<Aquarium> optional = aquariumRepository.findById(id);

        if (optional.isPresent()) {
            Aquarium aquarium = aquariumForm.updatedAquarium(id, aquariumRepository);
            return ResponseEntity.ok(AquariumDTO.toDto(aquarium));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "aquariumsList", allEntries = true)
    public ResponseEntity delete(@RequestHeader("Authorization") String bearerToken, @PathVariable Long id) {
        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Optional<Aquarium> optional = aquariumRepository.findByIdAndUserId(id, userId);

        if (optional.isPresent()) {
            aquariumRepository.deleteById(id);
            return ResponseEntity.ok().build();
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
