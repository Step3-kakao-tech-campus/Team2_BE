package com.example.team2_be.album;


import com.example.team2_be.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "album")
@Getter
@ToString
@NoArgsConstructor
public class Album extends BaseEntity {

    @Column(length = 32, nullable = false)
    private String albumName;

    @Column(length = 512, nullable = false)
    private String description;

    @Column(length = 512, nullable = false)
    private String image;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 16, nullable = false)
    private Category category;

    @Builder
    public Album (String albumName, String description, String image, Category category){
        this.albumName=albumName;
        this.description=description;
        this.image=image;
        this.category=category;
    }

    // 앨범 업데이트
    public void update (String albumName, String description, String image){
        this.albumName=albumName;
        this.description=description;
        this.image=image;
    }
}