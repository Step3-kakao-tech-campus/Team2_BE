package com.example.team2_be.auth;

import com.example.team2_be.auth.mail.MailService;
import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final MailService mailService;

    @PostMapping("/mail/send")
    public ResponseEntity<?> sendMail(@RequestBody AuthRequest.SendMailDTO sendMailDTO) throws Exception {
        mailService.sendSimpleMessage(sendMailDTO.getEmail());

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @PostMapping("/mail/check")
    public ResponseEntity<?> checkMail(@RequestBody AuthRequest.CheckMailDTO checkMailDTO) {
        mailService.checkAuthCode(checkMailDTO);

        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
