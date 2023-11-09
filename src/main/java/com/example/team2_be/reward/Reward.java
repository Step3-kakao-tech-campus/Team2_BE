package com.example.team2_be.reward;

import com.example.team2_be.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "rewards")
@Getter
@ToString
@NoArgsConstructor
public class Reward extends BaseEntity {
    @Column(name = "reward_name", length = 128, nullable = false)
    private String rewardName;

    @Column(length = 256, nullable = false)
    private String description;

    @Column(name = "reward_level", length = 16, nullable = false)
    private String level;

    @Column(name = "goal_count", nullable = false)
    private int goalCount;
}
