package com.example.team2_be.reward;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Sql(value = "classpath:import.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(value = {"classpath:application-test.yml"})
class RewardRepositoryTest {
    @Autowired
    RewardJPARepository rewardJPARepository;

    @Test
    @DisplayName("도전과제 조회 테스트")
    void find_all_reward_test() {
        //when
        List<Reward> rewards = rewardJPARepository.findAll();

        //then
        assertThat(rewards.isEmpty()).isEqualTo(false);
    }
}