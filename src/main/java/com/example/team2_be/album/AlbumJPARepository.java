package com.example.team2_be.album;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumJPARepository extends JpaRepository<Album, Long> {
    @Query("SELECT m FROM AlbumMember  m JOIN m.user u WHERE u.email = :email")
    List<Album> findAllByEmail(@Param("email") String email);

    //Album findByAlbumId(Long id);
    public Optional<Album> findById(Long id);
}
