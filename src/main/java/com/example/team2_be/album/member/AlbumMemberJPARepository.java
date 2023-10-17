package com.example.team2_be.album.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumMemberJPARepository extends JpaRepository<AlbumMember, Long> {

    AlbumMember findByUserIdAndAlbumId(@Param("userId") Long userId, @Param("albumId") Long albumId);

    List<AlbumMember> findAllByAlbumId(Long albumId);
}
