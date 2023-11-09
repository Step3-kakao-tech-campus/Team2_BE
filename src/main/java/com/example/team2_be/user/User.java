package com.example.team2_be.user;

import com.example.team2_be.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter
@ToString
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(length = 32, nullable = false, unique = true)
    private String nickname;

    @Column(length = 16)
    private String title;

    @Column(length = 512, nullable = false)
    private String image;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role", length = 16, nullable = false)
    private Role role;

    @Builder
    public User(Long id, String email, String nickname, String image, Role role) {
        super(id);
        this.email = email;
        this.nickname = nickname;
        this.image = image;
        this.role = role;
    }

    void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
