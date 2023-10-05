package com.example.team2_be.reward.progress;

import com.example.team2_be.reward.Reward;
import com.example.team2_be.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table
@Getter
@ToString
@NoArgsConstructor
public class Progress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int count;

    @Column(length = 1, nullable = false)
    private char success;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @Builder
    public Progress(Long id, char success, User user, Reward reward) {
        this.id = id;
        this.count = 0;
        this.success = success;
        this.user = user;
        this.reward = reward;
    }
}
