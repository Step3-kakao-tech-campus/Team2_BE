package com.example.team2_be.album;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumJPARepository extends JpaRepository<Album, Long> {
    Album findALLByEmail(String email);

}
