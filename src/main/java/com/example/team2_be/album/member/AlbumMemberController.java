package com.example.team2_be.album.member;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups/{groupId}/members")
public class AlbumMemberController {

    private final AlbumMemberService albumMemberService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> addMember(@PathVariable Long groupId, @PathVariable Long userId){
        albumMemberService.addUser(userId, groupId);

        return ResponseEntity.ok(null);
    }
}
