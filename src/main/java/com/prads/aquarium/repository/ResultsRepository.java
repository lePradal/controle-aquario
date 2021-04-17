package com.prads.aquarium.repository;

import com.prads.aquarium.models.Aquarium;
import com.prads.aquarium.models.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultsRepository extends JpaRepository<Result, Long> {
    Page<Result> findByAquariumId(Long aquariumId, Pageable pageable);
    Optional<Result> findByIdAndUserId(Long id, Long userId);
    Optional<Result> findByIdAndAquariumId(Long id, Long aquariumId);
}
