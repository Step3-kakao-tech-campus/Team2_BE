package com.example.team2_be.album.member;


import com.example.team2_be.album.dto.AlbumMemberFindResponseDTO;
import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums/{albumId}/members")
public class AlbumMemberController {

    private final AlbumMemberService albumMemberService;

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult<AlbumMemberFindResponseDTO>> getMembers(@PathVariable Long albumId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        albumMemberService.checkMembership(userId, albumId);

        AlbumMemberFindResponseDTO albumMemberFindResponseDTO = albumMemberService.getMembers(albumId);

        return ResponseEntity.ok(ApiUtils.success(albumMemberFindResponseDTO));
    }

    @PostMapping
    public ResponseEntity<Void> addMembers(@PathVariable Long albumId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        albumMemberService.addMembers(userId, albumId);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMembers(@PathVariable Long albumId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        albumMemberService.checkMembership(userId, albumId);

        albumMemberService.deleteMembers(userId, albumId);

        return ResponseEntity.ok(null);
    }
}
