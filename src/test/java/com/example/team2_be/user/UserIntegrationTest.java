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

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
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
    @DisplayName("유저 정보 조회 테스트")
    @WithUserDetails(value = "admin")
    void find_user_info_test() throws Exception {
        //given
        Long id = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(
                get("/users/{userId}", id)
        );

        //then
        resultActions.andExpect(jsonPath("$.success").value("true"))
                .andDo(print());
    }

    @Test
    @DisplayName("유저의 도전과제 조회 테스트")
    @WithUserDetails(value = "admin")
    void find_user_reward_test() throws Exception {
        //given
        Long id = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(
                get("/users/{userId}/rewards", id)
        );

        //then
        resultActions.andExpect(jsonPath("$.success").value("true"))
                .andDo(print());
    }

    @Test
    @DisplayName("유저의 칭호 조회 테스트")
    @WithUserDetails(value = "admin")
    void find_user_title_test() throws Exception {
        //given
        Long id = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(
                get("/users/{userId}/titles", id)
        );

        //then
        resultActions.andExpect(jsonPath("$.success").value("true"))
                .andDo(print());
    }

    @Test
    @DisplayName("유저의 칭호 변경 테스트")
    @WithUserDetails(value = "admin")
    void update_user_title_test() throws Exception {
        //given
        Long userId = 1L;
        Long titleId = 2L;

        //when
        ResultActions resultActions = mockMvc.perform(
                put("/users/{userId}/titles/{titleId}", userId, titleId)
        );

        //then
        resultActions.andExpect(jsonPath("$.success").value("true"))
                .andDo(print());
    }
}