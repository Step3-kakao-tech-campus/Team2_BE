package com.example.team2_be.user;

import com.example.team2_be.kakao.dto.KakaoAccount;
import com.example.team2_be.reward.Reward;
import com.example.team2_be.reward.RewardJPARepository;
import com.example.team2_be.reward.progress.Progress;
import com.example.team2_be.reward.progress.ProgressJPARepository;
import com.example.team2_be.title.collection.Collection;
import com.example.team2_be.title.collection.CollectionJPARepository;
import com.example.team2_be.user.dto.UserInfoFindResponseDTO;
import com.example.team2_be.user.dto.UserInfoUpdateRequestDTO;
import com.example.team2_be.user.dto.UserRewardFindResponseDTO;
import com.example.team2_be.user.dto.UserTitleFindResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserJPARepository userJPARepository;
    private final RewardJPARepository rewardJPARepository;
    private final ProgressJPARepository progressJPARepository;
    private final CollectionJPARepository collectionJPARepository;

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

    public UserRewardFindResponseDTO findUserReward(User user) {
        List<Reward> findRewards = rewardJPARepository.findAll();

        User findUser = userJPARepository.findByEmail(user.getEmail());
        List<Progress> findProgresses = progressJPARepository.findByUserId(findUser.getId());

        return new UserRewardFindResponseDTO(findRewards, findProgresses);
    }

    public UserTitleFindResponseDTO findUserTitle(User user) {
        User findUser = userJPARepository.findByEmail(user.getEmail());
        List<Collection> collections = collectionJPARepository.findByUserId(findUser.getId());

        return new UserTitleFindResponseDTO(collections);
    }

    @Transactional
    public void updateUserTitle(Long id, User user) {
        User findUser = userJPARepository.findByEmail(user.getEmail());

        Collection findCollection = collectionJPARepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 칭호를 찾을 수 없음"));

        findUser.updateTitle(findCollection.getTitle().getTitleName());

        System.out.println("findUser = " + findUser);
    }
}
