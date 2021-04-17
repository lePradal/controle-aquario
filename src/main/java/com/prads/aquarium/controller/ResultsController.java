package com.prads.aquarium.controller;

import com.prads.aquarium.config.security.TokenService;
import com.prads.aquarium.controller.dto.AquariumDTO;
import com.prads.aquarium.controller.dto.AquariumDetailDTO;
import com.prads.aquarium.controller.form.AquariumForm;
import com.prads.aquarium.controller.form.ResultForm;
import com.prads.aquarium.controller.form.UpdateAquariumForm;
import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.Result;
import com.prads.aquarium.models.User;
import com.prads.aquarium.repository.AquariumRepository;
import com.prads.aquarium.repository.ResultsRepository;
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
@RequestMapping(path = "/results")
public class ResultsController {

    @Autowired
    private AquariumRepository aquariumRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResultsRepository resultsRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Result> detail(@RequestHeader("Authorization") String bearerToken, @PathVariable Long id) throws NotFoundException {
        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Optional<Result> optional = resultsRepository.findById(id);

        if (!optional.isPresent()) {
            throw new NotFoundException("Invalid Result!");
        }

        Long aquariumId = optional.get().getAquarium().getId();

        Optional<Aquarium> optionalAquarium = aquariumRepository.findByIdAndUserId(aquariumId, userId);

        if (!optionalAquarium.isPresent()) {
            throw new NotFoundException("Invalid Aquarium!");
        }

        return optional
                .map(result -> ResponseEntity.ok(result))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/list/{aquariumId}")
    public ResponseEntity<Page<Result>> resultsList(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Long aquariumId,
            @PageableDefault(page = 0, size = 5, sort = "creationDate", direction = Direction.DESC) Pageable pageable
    ) throws NotFoundException {
        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Optional<Aquarium> optional = aquariumRepository.findByIdAndUserId(aquariumId, userId);

        if (!optional.isPresent()) {
            throw new NotFoundException("Invalid Aquarium!");
        }

        Page<Result> resultsPage;
        resultsPage = resultsRepository.findByAquariumId(aquariumId, pageable);

        if (resultsPage.getTotalElements() > 0) {
            return ResponseEntity.ok(resultsPage);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "resultsList", allEntries = true)
    public ResponseEntity<Result> register(@RequestHeader("Authorization") String bearerToken, @RequestBody @Valid ResultForm resultForm, UriComponentsBuilder uriBuilder) throws NotFoundException {
        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Invalid User!");
        }

        Optional<Aquarium> aquarium = aquariumRepository.findById(resultForm.getAquariumId());

        if (!aquarium.isPresent()) {
            throw new NotFoundException("Invalid Aquarium!");
        }

        Result result = resultForm.toResult(user.get(), aquarium.get());
        resultsRepository.save(result);

        URI uri = uriBuilder.path("/results/{aquariumId}").buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(uri).body(result);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "aquariumsList", allEntries = true)
    public ResponseEntity delete(@RequestHeader("Authorization") String bearerToken, @PathVariable Long id) {
        String token = recoverToken(bearerToken);
        Long userId = tokenService.getUserId(token);

        Optional<Result> optional = resultsRepository.findByIdAndUserId(id, userId);

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
