package com.example.team2_be.user;

import com.example.team2_be.kakao.dto.KakaoAccount;
import com.example.team2_be.user.dto.UserInfoFindResponseDTO;
import com.example.team2_be.user.dto.UserInfoUpdateRequestDTO;
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

    public UserInfoFindResponseDTO findUser(User user) {
        User findUser = userJPARepository.findByEmail(user.getEmail());

        // 예외 처리 : 유저 찾기 실패

        return new UserInfoFindResponseDTO(findUser);
    }

    @Transactional
    public void updateUserInfo(UserInfoUpdateRequestDTO updateDTO, User user) {
        User findUser = userJPARepository.findByEmail(user.getEmail());

        // 예외 처리 : 본인이 맞는지 권한 체크

        findUser.update(updateDTO.getNewNickname());
    }
}
