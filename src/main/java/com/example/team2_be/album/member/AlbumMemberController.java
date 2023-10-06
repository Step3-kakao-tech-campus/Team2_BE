package com.example.team2_be.album.member;


import com.example.team2_be.core.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups/{id}/members")
public class AlbumMemberController {

    private final AlbumMemberService albumMemberService;

    @PostMapping
    public ResponseEntity<Void> add(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails userDetails){
        albumMemberService.addUser(userDetails.getUser(), id);

        return ResponseEntity.ok(null);
    }
}
