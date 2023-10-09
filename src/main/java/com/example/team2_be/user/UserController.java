package com.example.team2_be.user;

import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import com.example.team2_be.user.dto.UserInfoFindResponseDTO;
import com.example.team2_be.user.dto.UserInfoUpdateRequestDTO;
import com.example.team2_be.user.dto.UserRewardFindResponseDTO;
import com.example.team2_be.user.dto.UserTitleFindResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiUtils.ApiResult<UserInfoFindResponseDTO>> find(@PathVariable Long userId) {
        UserInfoFindResponseDTO findDTO = userService.findUserInfo(userId);

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Long userId, @RequestBody @Valid UserInfoUpdateRequestDTO updateDTO) {
        userService.updateUserInfo(updateDTO, userId);

        return ResponseEntity.ok(null);
    }

    @GetMapping("/{userId}/rewards")
    public ResponseEntity<?> findReward(@PathVariable Long userId) {
        UserRewardFindResponseDTO rewardFindResponseDTO = userService.findUserReward(userId);

        return ResponseEntity.ok(ApiUtils.success(rewardFindResponseDTO));
    }

    @GetMapping("/{userId}/titles")
    public ResponseEntity<?> findTitle(@PathVariable Long userId) {
        UserTitleFindResponseDTO titleFindResponseDTO = userService.findUserTitle(userId);

        return ResponseEntity.ok(ApiUtils.success(titleFindResponseDTO));
    }

    @PutMapping("/titles/{id}")
    public ResponseEntity<ApiUtils.ApiResult<Object>> updateTitle(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.updateUserTitle(id, userDetails.getUser());

        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
