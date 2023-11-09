package com.example.team2_be.album.page;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.album.Album;
import com.example.team2_be.album.page.image.AlbumPageImage;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.OneToMany;
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

    @Column(columnDefinition = "longtext")
    private String shapes;

    @Column(columnDefinition = "longtext")
    private String bindings;

    @Column(length = 512)
    private String capturePageUrl;

    @OneToMany(mappedBy = "albumPage")
    private List<AlbumPageImage> albumPageImages = new ArrayList<>();

    @Builder
    public AlbumPage(Album album) {
        this.album = album;
    }

    void updateAlbumPage(String shapes, String bindings, String capturePageUrl) {
        this.shapes = shapes;
        this.bindings = bindings;
        this.capturePageUrl = capturePageUrl;
    }
}
