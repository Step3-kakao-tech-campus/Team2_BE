package com.example.team2_be.auth;
import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/kakao/login")
    public @ResponseBody ResponseEntity<ApiUtils.ApiResult<String>> kakaoLogin(@RequestBody String code){
        return ResponseEntity.ok(ApiUtils.success(authService.kakaoLogin(code)));
    }

    @PostMapping("/google/login")
    public @ResponseBody ResponseEntity<ApiUtils.ApiResult<String>> googleLogin(@RequestBody String code){
        return ResponseEntity.ok(ApiUtils.success(authService.googleLogin(code)));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiUtils.ApiResult<Void>> logout(){
        authService.logout();
        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
