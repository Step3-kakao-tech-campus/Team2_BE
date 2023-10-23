package com.example.team2_be.trash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrashJPARepository extends JpaRepository<Trash, Long> {
    @Query("select t from Trash t where t.albumPage.album.id = :albumId")
    List<Trash> findAllByAlbumId(@Param("albumId") Long albumId);
}
