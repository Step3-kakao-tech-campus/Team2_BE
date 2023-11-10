package com.example.team2_be.user;

import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import com.example.team2_be.user.dto.UserInfoFindResponseDTO;
import com.example.team2_be.user.dto.UserInfoUpdateRequestDTO;
import com.example.team2_be.user.dto.UserRewardFindResponseDTO;
import com.example.team2_be.user.dto.UserTitleFindResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<ApiUtils.ApiResult<UserInfoFindResponseDTO>> find(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        UserInfoFindResponseDTO findDTO = userService.findUserInfo(customUserDetails.getUser().getId());

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody @Valid UserInfoUpdateRequestDTO updateDTO) {
        userService.updateUserInfo(updateDTO, userId);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @GetMapping("/{userId}/rewards")
    public ResponseEntity<?> findReward(@PathVariable Long userId, @PageableDefault(size = 10) Pageable pageable) {
        UserRewardFindResponseDTO rewardFindResponseDTO = userService.findUserReward(userId, pageable);

        return ResponseEntity.ok(ApiUtils.success(rewardFindResponseDTO));
    }

    @GetMapping("/{userId}/titles")
    public ResponseEntity<?> findTitle(@PathVariable Long userId) {
        UserTitleFindResponseDTO titleFindResponseDTO = userService.findUserTitle(userId);

        return ResponseEntity.ok(ApiUtils.success(titleFindResponseDTO));
    }

    @PutMapping("/{userId}/titles/{titleId}")
    public ResponseEntity<ApiUtils.ApiResult<Object>> updateTitle(@PathVariable Long userId, @PathVariable Long titleId) {
        userService.updateUserTitle(userId, titleId);

        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
