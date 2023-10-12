package com.example.team2_be.auth;

import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/kakao/login")
    public @ResponseBody ResponseEntity<ApiUtils.ApiResult<String>> kakaoLogin(@RequestBody String code){
        return ResponseEntity.ok(ApiUtils.success(authService.kakaoLogin(code)));
    }
}
