package com.example.team2_be.album.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumMemberJPARepository extends JpaRepository<AlbumMember, Long> {
    public AlbumMember findByUserAndGroup(Long userId, Long groupId);
}
