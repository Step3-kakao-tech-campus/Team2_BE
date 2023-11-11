package com.example.team2_be.album;

import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.album.dto.AlbumFindAllResponseDTO;
import com.example.team2_be.album.dto.AlbumUpdaterequestDTO;
import com.example.team2_be.album.member.AlbumMemberService;
import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums")
public class AlbumController {
    private final AlbumService albumService;
    private final AlbumMemberService albumMemberService;

    // 앨범 생성기능 POST "/albums"
    @PostMapping
    public ResponseEntity<ApiUtils.ApiResult> createAlbum(@RequestBody @Valid AlbumCreateRequestDTO requestDTO, Error errors, @AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {

        Long userId = userDetails.getUser().getId();
        Album newAlbum = albumService.createAlbum(requestDTO);
        // album을 생성하는 유저를 albumMember로 추가
        albumMemberService.addMembers(userId, newAlbum.getId());

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // 앨범 수정 기능 PUT "/albums/"
    @PutMapping("/{albumId}")
    public ResponseEntity<ApiUtils.ApiResult> updateAlbum (@RequestBody AlbumUpdaterequestDTO requestDTO, @PathVariable Long albumId, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        albumMemberService.checkMembership(userId, albumId);
        albumService.updateAlbum(requestDTO,albumId);

        return ResponseEntity.ok(ApiUtils.success(null));
    }

    // 앨범 조회 기능 GET "/albums"
    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult<AlbumFindAllResponseDTO>> findAllAlbum (@AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        AlbumFindAllResponseDTO findDTO = albumService.findAllAlbum(userId);

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }
}
