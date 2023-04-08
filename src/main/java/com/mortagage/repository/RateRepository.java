package com.mortagage.repository;

import com.mortagage.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findByMaturityPeriod(int maturityPeriod);
}
