package com.example.team2_be.title.collection;

import com.example.team2_be.title.Title;
import com.example.team2_be.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@ToString
@NoArgsConstructor
public class Collection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime creatAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Title title;

    @Builder
    public Collection(Long id, User user, Title title) {
        this.id = id;
        this.creatAt = LocalDateTime.now();
        this.user = user;
        this.title = title;
    }
}
