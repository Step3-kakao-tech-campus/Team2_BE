package com.example.team2_be.user.dto;

import com.example.team2_be.reward.Reward;
import com.example.team2_be.reward.progress.Progress;
import lombok.Getter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
public class UserRewardFindResponseDTO {
    List<RewardDTO> rewards;

    public UserRewardFindResponseDTO(List<Reward> rewards, List<Progress> progresses) {
        this.rewards = rewards.stream()
                .map(reward -> new RewardDTO(reward, progresses))
                .collect(Collectors.toList());
    }
}

@Getter
class RewardDTO {
    private Long id;
    private String rewardName;
    private String description;
    private String level;
    private int goalCount;
    private int count;
    private char success;

    public RewardDTO(Reward reward, List<Progress> progresses) {
        this.id = reward.getId();
        this.rewardName = reward.getRewardName();
        this.description = reward.getDescription();
        this.level = reward.getLevel();
        this.goalCount = reward.getGoalCount();

        Optional<Progress> findProgress = progresses.stream()
                .filter(progress -> progress.getReward().getId().equals(reward.getId()))
                .findFirst();

        Progress thisProgress = new Progress();

        if(findProgress.isPresent()) {
            thisProgress = findProgress.get();
        }

        this.count = thisProgress.getCount();
        this.success = thisProgress.getSuccess();
    }
}