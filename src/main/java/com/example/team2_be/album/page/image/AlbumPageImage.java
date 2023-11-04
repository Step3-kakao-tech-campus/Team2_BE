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
@Table(name = "albumImage")
@Getter
@ToString
@NoArgsConstructor
public class AlbumPageImage extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "AlbumPage")
    private AlbumPage albumPage;

    @Column(length = 128, nullable = false, unique = true)
    private String assetId;

    @Column(length = 128, nullable = false, unique = true)
    private String fileName;

    @Column(length = 16, nullable = false)
    private String type;

    @Column(nullable = false)
    private double xSize;

    @Column(nullable = false)
    private double ySize;

    @Builder
    public AlbumPageImage(AlbumPage albumPage, String assetId, String fileName, String type, double xSize, double ySize) {
        this.albumPage = albumPage;
        this.assetId = assetId;
        this.fileName = fileName;
        this.type = type;
        this.xSize = xSize;
        this.ySize = ySize;
    }
}
