package com.example.team2_be.title.collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionJPARepository extends JpaRepository<Collection, Long> {
    @Query("select c from Collection c where c.user.id = :id")
    List<Collection> findByUserId(@Param("id") Long id);
}
