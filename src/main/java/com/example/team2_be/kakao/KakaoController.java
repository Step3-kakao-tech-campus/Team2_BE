package com.example.team2_be.kakao;

import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/auth/login")
    public String kakaoLogin(){
        String authCodeUrl = kakaoService.getAuthCodeUrl();
        return "redirect:" + authCodeUrl;
    }

    @GetMapping("/callback")
    public ResponseEntity<?> getKakaoAccount(@RequestParam("code") String code){
        KakaoToken kakaoToken = kakaoService.getToken(code);
        return ResponseEntity.ok(ApiUtils.success(kakaoToken));
    }
}
