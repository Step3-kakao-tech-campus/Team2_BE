package com.example.team2_be.album.member;


import com.example.team2_be.album.dto.AlbumMemberFindResponseDTO;
import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums/{albumId}/members")
public class AlbumMemberController {

    private final AlbumMemberService albumMemberService;

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult<AlbumMemberFindResponseDTO>> getMembers(@PathVariable Long albumId) {
        AlbumMemberFindResponseDTO albumMemberFindResponseDTO = albumMemberService.getMembers(albumId);

        return ResponseEntity.ok(ApiUtils.success(albumMemberFindResponseDTO));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> addMembers(@PathVariable Long albumId, @PathVariable Long userId) {
        albumMemberService.addMembers(userId, albumId);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteMembers(@PathVariable Long albumId, @PathVariable Long userId) {
        albumMemberService.deleteMembers(userId, albumId);

        return ResponseEntity.ok(null);
    }
}
