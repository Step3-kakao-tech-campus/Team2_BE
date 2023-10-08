package com.example.team2_be.album;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumJPARepository extends JpaRepository<Album, Long> {
    List<Album> findALLByEmail(String email);
    Album findByAlbumId(Long id);
}
