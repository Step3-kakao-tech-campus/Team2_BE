package com.example.team2_be.user;

import com.example.team2_be.title.Title;
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

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 32, nullable = false, unique = true)
    private String nickname;

    @Column(length = 16)
    private String title;

    @Column(length = 512, nullable = false)
    private String image;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 16, nullable = false)
    private Role role;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Builder
    public User(Long id, String email, String nickname, String image, Role role, LocalDateTime createAt) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.image = image;
        this.role = role;
        this.createAt = createAt;
    }

    void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
