package com.example.team2_be.album.page;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.album.Album;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "albumPage")
@Getter
@ToString
@NoArgsConstructor
public class AlbumPage extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
}
