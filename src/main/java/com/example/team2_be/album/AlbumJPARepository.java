package com.example.team2_be.album;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlbumJPARepository extends JpaRepository<Album, Long> {
    @Query("SELECT m FROM AlbumMember  m JOIN m.user u WHERE u.id = :id")
    List<Album> findAllByUserId(@Param("id") Long id);

    Optional<Album> findById(Long id);
}
