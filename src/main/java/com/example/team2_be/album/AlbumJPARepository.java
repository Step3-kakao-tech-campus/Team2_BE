package com.example.team2_be.album;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlbumJPARepository extends JpaRepository<Album, Long> {
    List<Album> findAllByEmail(String email);
    Album findByAlbumId(Long id);
    public Optional<Album> findById(Long id);
}
