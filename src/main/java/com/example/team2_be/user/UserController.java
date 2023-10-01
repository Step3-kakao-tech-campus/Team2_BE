package com.example.team2_be.user;

import com.example.team2_be.auth.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/find")
    public ResponseEntity<?> find(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserResponse.FindDTO findDTO = userService.findUser(userDetails.getUser());

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }
}
