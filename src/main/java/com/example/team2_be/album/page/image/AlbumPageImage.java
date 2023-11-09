package com.example.team2_be.album.page.image;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.album.page.AlbumPage;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "album_images")
@Getter
@ToString
@NoArgsConstructor
public class AlbumPageImage extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "album_page_id")
    private AlbumPage albumPage;

    @Column(name = "asset_id", length = 128, nullable = false)
    private String assetId;

    @Column(name = "file_name", length = 128, nullable = false)
    private String fileName;

    @Column(length = 16, nullable = false)
    private String type;

    @Column(name = "x_size", nullable = false)
    private double xSize;

    @Column(name = "y_size", nullable = false)
    private double ySize;

    @Column(length = 2056)
    private String url;

    @Builder
    public AlbumPageImage(AlbumPage albumPage, String assetId, String fileName, String type, double xSize, double ySize, String url) {
        this.albumPage = albumPage;
        this.assetId = assetId;
        this.fileName = fileName;
        this.type = type;
        this.xSize = xSize;
        this.ySize = ySize;
        this.url = url;
    }

    public void setAlbumPage() {
        if (this.albumPage != null) {
            this.albumPage.getAlbumPageImages().remove(this);
        }
        this.albumPage = albumPage;
        albumPage.getAlbumPageImages().add(this);
    }
}
