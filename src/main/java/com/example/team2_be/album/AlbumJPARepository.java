package com.example.team2_be.album;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumJPARepository extends JpaRepository<Album, Long> {

    Optional<Album> findById(Long id);
}
