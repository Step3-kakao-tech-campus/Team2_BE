package com.example.team2_be.reward.progress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgressJPARepository extends JpaRepository<Progress, Long> {
    @Query("select p from Progress p where p.user.id = :id")
    List<Progress> findByUserId(@Param("id") Long id);
}
