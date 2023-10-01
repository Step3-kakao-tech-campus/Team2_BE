package com.example.team2_be.user;

import com.example.team2_be.kakao.DTO.KakaoAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserJPARepository userJPARepository;
    public static final String DEFAULT_IMAGE_URL = "";

    @Transactional
    public User checkUser(KakaoAccount kakaoAccount) {
        // DB 안의 user 정보 확인
        User user = userJPARepository.findByEmail(kakaoAccount.getEmail());

        if(user == null){
            return saveUser(kakaoAccount);
        }
        return user;
    }

    private User saveUser(KakaoAccount kakaoAccount){
        // 없을 경우 생성 및 추가
        User newUser = User.builder()
                .email(kakaoAccount.getEmail())
                .nickname(kakaoAccount.getProfile().getNickname())
                .image(DEFAULT_IMAGE_URL)
                .role(Role.ROLE_USER)
                .createAt(LocalDateTime.now())
                .build();
        userJPARepository.save(newUser);

        return newUser;
    }
}
