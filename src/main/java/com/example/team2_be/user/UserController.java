package com.example.team2_be.user;

import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import com.example.team2_be.user.dto.UserInfoFindResponseDTO;
import com.example.team2_be.user.dto.UserInfoUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/find")
    public ResponseEntity<?> find(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserInfoFindResponseDTO findDTO = userService.findUser(userDetails.getUser());

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserInfoUpdateRequestDTO updateDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.updateUserInfo(updateDTO, userDetails.getUser());

        return ResponseEntity.ok(null);
    }
}
