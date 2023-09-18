package com.example.team2_be.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@ToString
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false, unique = true)
    private String userId;

    @Column(length = 512, nullable = false)
    private String password;

    @Column(length = 32, nullable = false, unique = true)
    private String nickname;

    @Column(length = 512, nullable = false)
    private String image;

    @Column(length = 16, nullable = false)
    private Role role;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Builder
    public User(Long id, String userId, String password, String nickname, String image, Role role, LocalDateTime createAt) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.image = image;
        this.role = role;
        this.createAt = createAt;
    }
}
