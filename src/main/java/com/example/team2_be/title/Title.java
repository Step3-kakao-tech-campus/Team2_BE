package com.example.team2_be.title;

import com.example.team2_be.BaseEntity;
import com.example.team2_be.reward.Reward;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "titles")
@Getter
@ToString
@NoArgsConstructor
public class Title extends BaseEntity {
    @Column(name = "title_name", length = 16, nullable = false)
    private String titleName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private Reward reward;
}
