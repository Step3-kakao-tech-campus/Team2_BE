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

    @GetMapping("/find")
    public ResponseEntity<?> find(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserInfoFindResponseDTO findDTO = userService.findUserInfo(userDetails.getUser());

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UserInfoUpdateRequestDTO updateDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.updateUserInfo(updateDTO, userDetails.getUser());

        return ResponseEntity.ok(null);
    }

    @GetMapping("/rewards")
    public ResponseEntity<?> findReward(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserRewardFindResponseDTO rewardFindResponseDTO = userService.findUserReward(userDetails.getUser());

        return ResponseEntity.ok(ApiUtils.success(rewardFindResponseDTO));
    }

    @GetMapping("/titles")
    public ResponseEntity<?> findTitle(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserTitleFindResponseDTO titleFindResponseDTO = userService.findUserTitle(userDetails.getUser());

        return ResponseEntity.ok(ApiUtils.success(titleFindResponseDTO));
    }

    @PutMapping("/titles/{id}")
    public ResponseEntity<ApiUtils.ApiResult<Object>> updateTitle(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.updateUserTitle(id, userDetails.getUser());

        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
