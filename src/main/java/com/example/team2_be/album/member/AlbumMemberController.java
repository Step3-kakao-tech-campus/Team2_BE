package com.example.team2_be.album.member;


import com.example.team2_be.album.dto.AlbumMemberFindResponseDTO;
import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/groups/{groupId}/members")
public class AlbumMemberController {

    private final AlbumMemberService albumMemberService;

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult<AlbumMemberFindResponseDTO>> getMembers(@PathVariable Long groupId){
        AlbumMemberFindResponseDTO albumMemberFindResponseDTO = albumMemberService.findMembers(groupId);

        return ResponseEntity.ok(ApiUtils.success(albumMemberFindResponseDTO));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> addMember(@PathVariable Long groupId, @PathVariable Long userId){
        albumMemberService.addUser(userId, groupId);

        return ResponseEntity.ok(null);
    }
}
