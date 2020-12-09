package com.prads.aquarium.repository;

import com.prads.aquarium.models.Aquarium;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AquariumRepository extends JpaRepository<Aquarium, Long> {
    Page<Aquarium> findByUserId(Long userId, Pageable pageable);
    Optional<Aquarium> findByIdAndUserId(Long id, Long userId);
}
