package com.example.team2_be.album.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumMemberJPARepository extends JpaRepository<AlbumMember, Long> {

    List<AlbumMember> findAllByAlbumId(Long albumId);

    List<AlbumMember> findAllByUserId(Long userId);

    AlbumMember findByAlbumIdAndUserId(@Param("albumId") Long albumId, @Param("userId") Long userId);
}
