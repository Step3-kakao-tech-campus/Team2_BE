package com.example.team2_be.reward;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardJPARepository extends JpaRepository<Reward, Long> {
    Page<Reward> findAll(Pageable pageable);
}
