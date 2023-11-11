package com.example.team2_be.user;


import com.example.team2_be.core.error.exception.NotFoundException;
import com.example.team2_be.auth.dto.UserAccountDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User getUser(UserAccountDTO userAccount ) {
        // DB 안의 user 정보 확인
        User user = userJPARepository.findByEmail(userAccount.getEmail());

        if(user == null){
            return saveUser(userAccount);
        }
        return user;
    }

    private User saveUser(UserAccountDTO userAccount){
        // 없을 경우 생성 및 추가
        User newUser = User.builder()
                .email(userAccount.getEmail())
                .nickname(userAccount.getNickname())
                .image(DEFAULT_IMAGE_URL)
                .role(Role.ROLE_USER)
                .build();
        userJPARepository.save(newUser);

        return newUser;
    }

    public UserInfoFindResponseDTO findUserInfo(Long id) {
        User findUser = userJPARepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));
        List<Collection> collections = collectionJPARepository.findByUserId(findUser.getId());

        return new UserInfoFindResponseDTO(findUser, collections);
    }

    @Transactional
    public void updateUserInfo(UserInfoUpdateRequestDTO updateDTO, Long id) {
        User findUser = userJPARepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        findUser.updateNickname(updateDTO.getNewNickname());
    }

    public UserRewardFindResponseDTO findUserReward(Long id, Pageable pageable) {
        Page<Reward> findRewards = rewardJPARepository.findAll(pageable);

        User findUser = userJPARepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));
        List<Progress> findProgresses = progressJPARepository.findByUserId(findUser.getId());

        return new UserRewardFindResponseDTO(findRewards.getContent(), findProgresses);
    }

    public UserTitleFindResponseDTO findUserTitle(Long id) {
        User findUser = userJPARepository.findById(id)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));
        List<Collection> collections = collectionJPARepository.findByUserId(findUser.getId());

        return new UserTitleFindResponseDTO(collections);
    }

    @Transactional
    public void updateUserTitle(Long userId, Long titleId) {
        User findUser = userJPARepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));

        Collection findCollection = collectionJPARepository.findById(titleId)
                .orElseThrow(() -> new NotFoundException("해당 칭호를 찾을 수 없습니다."));

        findUser.updateTitle(findCollection.getTitle().getTitleName());
    }
}
