package com.example.team2_be.user;

import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import com.example.team2_be.user.dto.UserInfoFindResponseDTO;
import com.example.team2_be.user.dto.UserInfoUpdateRequestDTO;
import com.example.team2_be.user.dto.UserRewardFindResponseDTO;
import com.example.team2_be.user.dto.UserTitleFindResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult<UserInfoFindResponseDTO>> find(@AuthenticationPrincipal CustomUserDetails UserDetails) {
        UserInfoFindResponseDTO findDTO = userService.findUserInfo(UserDetails.getUser().getId());

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody @Valid UserInfoUpdateRequestDTO updateDTO) {
        Long userId = userDetails.getUser().getId();

        userService.updateUserInfo(updateDTO, userId);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @GetMapping("/rewards")
    public ResponseEntity<?> findReward(@AuthenticationPrincipal CustomUserDetails userDetails, @PageableDefault(size = 10) Pageable pageable) {
        Long userId = userDetails.getUser().getId();
        UserRewardFindResponseDTO rewardFindResponseDTO = userService.findUserReward(userId, pageable);

        return ResponseEntity.ok(ApiUtils.success(rewardFindResponseDTO));
    }

    @GetMapping("/titles")
    public ResponseEntity<?> findTitle(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        UserTitleFindResponseDTO titleFindResponseDTO = userService.findUserTitle(userId);

        return ResponseEntity.ok(ApiUtils.success(titleFindResponseDTO));
    }

    @PutMapping("/titles/{titleId}")
    public ResponseEntity<ApiUtils.ApiResult<Object>> updateTitle(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long titleId) {
        Long userId = userDetails.getUser().getId();
        userService.updateUserTitle(userId, titleId);

        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
