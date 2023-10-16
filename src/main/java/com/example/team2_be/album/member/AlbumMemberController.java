package com.example.team2_be.album.member;



import com.example.team2_be.album.dto.AlbumMemberFindResponseDTO;
import com.example.team2_be.core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums/{albumId}/members")
public class AlbumMemberController {

    private final AlbumMemberService albumMemberService;

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult<AlbumMemberFindResponseDTO>> getMembers(@PathVariable Long albumId){
        AlbumMemberFindResponseDTO albumMemberFindResponseDTO = albumMemberService.findMembers(albumId);

        return ResponseEntity.ok(ApiUtils.success(albumMemberFindResponseDTO));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Void> addMember(@PathVariable Long albumId, @PathVariable Long userId){
        albumMemberService.addMember(userId, albumId);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long albumId, @PathVariable Long userId){
        albumMemberService.deleteMember(userId, albumId);

        return ResponseEntity.ok(null);
    }
}
