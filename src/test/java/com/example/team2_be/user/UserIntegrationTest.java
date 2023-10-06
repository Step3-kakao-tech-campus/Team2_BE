package com.example.team2_be.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql(value = "classpath:import.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(value = {"classpath:application-test.yml"})
class UserIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("유저의 도전과제 조회 테스트")
    @WithUserDetails(value = "admin")
    void find_user_reward_test() throws Exception {
        //when
        ResultActions resultActions = mockMvc.perform(
                get("/users/rewards")
        );

        //then
        resultActions.andExpect(jsonPath("$.success").value("true"))
                .andDo(print());
    }

    @Test
    @DisplayName("유저의 칭호 조회 테스트")
    @WithUserDetails(value = "admin")
    void find_user_title_test() throws Exception {
        //when
        ResultActions resultActions = mockMvc.perform(
                get("/users/titles")
        );

        //then
        resultActions.andExpect(jsonPath("$.success").value("true"))
                .andDo(print());
    }
}