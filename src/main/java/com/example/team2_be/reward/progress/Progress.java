package com.example.team2_be.reward.progress;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.reward.Reward;
import com.example.team2_be.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter
@ToString
@NoArgsConstructor
public class Progress extends BaseEntity {
    @Column(nullable = false)
    private int count;

    @Column(length = 1, nullable = false)
    private char success;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @Builder
    public Progress(char success, User user, Reward reward) {
        this.count = 0;
        this.success = success;
        this.user = user;
        this.reward = reward;
    }
}
