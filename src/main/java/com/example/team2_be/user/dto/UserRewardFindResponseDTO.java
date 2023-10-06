package com.example.team2_be.user.dto;

import com.example.team2_be.reward.Reward;
import com.example.team2_be.reward.progress.Progress;
import lombok.Getter;

import java.util.List;
import java.util.Map;
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