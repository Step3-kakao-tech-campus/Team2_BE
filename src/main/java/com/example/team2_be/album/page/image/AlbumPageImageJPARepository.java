package com.example.team2_be.album.page.image;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlbumPageImageJPARepository extends JpaRepository<AlbumPageImage, Long> {
    //@Query("select t from Trash t where t.albumPage.album.id = :albumId")
    @Query("select a from AlbumPageImage a where a.albumPage.id = :pageId")
    List<AlbumPageImage> findAllById(@Param("pageId") Long pageId);
}
