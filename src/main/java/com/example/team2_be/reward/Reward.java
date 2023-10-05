package com.example.team2_be.reward;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter
@ToString
@NoArgsConstructor
public class Reward {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false)
    private String rewardName;

    @Column(length = 256, nullable = false)
    private String description;

    @Column(length = 16, nullable = false)
    private String level;

    @Column(nullable = false)
    private int goalCount;
}
