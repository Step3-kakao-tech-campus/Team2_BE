package com.example.team2_be.album.page;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.album.Album;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "albumPage")
@Getter
@ToString
@NoArgsConstructor
public class AlbumPage extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(columnDefinition = "longtext", nullable = false)
    private String shapes;

    @Column(columnDefinition = "longtext")
    private String bindings;

    @Builder
    public AlbumPage(Album album, String shapes, String bindings) {
        this.album = album;
        this.shapes = shapes;
        this.bindings = bindings;
    }
}
