package com.example.team2_be.album.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumMemberJPARepository extends JpaRepository<AlbumMember, Long> {
    @Query("select m from AlbumMember m where m.user.id = :userId and m.album.id = :groupId")
    AlbumMember findByUserIdAndAlbumId(@Param("userId") Long userId, @Param("groupId") Long groupId);

    List<AlbumMember> findAllByAlbumId(Long groupId);
}
