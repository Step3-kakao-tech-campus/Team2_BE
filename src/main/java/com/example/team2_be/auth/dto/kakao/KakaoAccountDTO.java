package com.example.team2_be.auth.dto.kakao;

import com.example.team2_be.auth.dto.UserAccountDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAccountDTO extends UserAccountDTO {
}
