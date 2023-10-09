package com.example.team2_be.title;

import org.assertj.core.api.Assertions;
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
class TitleRepositoryTest {
    @Autowired
    TitleJPARepository titleJPARepository;

    @Test
    @DisplayName("칭호 조회 테스트")
    void find_all_title_test() {
        //when
        List<Title> titles = titleJPARepository.findAll();

        //then
        assertThat(titles.isEmpty()).isEqualTo(false);
    }
}