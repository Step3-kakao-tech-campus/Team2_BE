package com.example.team2_be.title.collection;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.title.Title;
import com.example.team2_be.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "collections", indexes = {@Index(name = "idx_user_id", columnList = "user_id"), @Index(name = "idx_title_id", columnList = "title_id")})
@Getter
@ToString
@NoArgsConstructor
public class Collection extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Title title;

    @Builder
    public Collection(User user, Title title) {
        this.user = user;
        this.title = title;
    }
}
