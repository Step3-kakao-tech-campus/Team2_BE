package com.example.team2_be.album;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "album")
@Getter
@ToString
@NoArgsConstructor
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32, nullable = false)
    private String albumName;

    @Column(length = 512, nullable = false)
    private String description;

    @Column(length = 512, nullable = false)
    private String image;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 16, nullable = false)
    private Category category;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Builder
    public Album (Long id, String albumName, String description, String image, Category category, LocalDateTime createAt){
        this.id= id;
        this.albumName=albumName;
        this.description=description;
        this.image=image;
        this.category=category;
        this.createAt=createAt;
    }
}
