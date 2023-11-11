package com.example.team2_be.trash;

import com.example.team2_be.album.member.AlbumMemberService;
import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import com.example.team2_be.trash.dto.TrashesFindResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums/{albumId}/trashes")
public class TrashController {
    private final TrashService trashService;
    private final AlbumMemberService albumMemberService;

    //휴지통 조회 GET
    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult<TrashesFindResponseDTO>> findTrashes (@PathVariable Long albumId, @PageableDefault(size = 4) Pageable pageable, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        albumMemberService.checkMembership(userId, albumId);

        TrashesFindResponseDTO findDTO = trashService.findTrashes(albumId, pageable);

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }

    @PostMapping("/{trashId}")
    public ResponseEntity<ApiUtils.ApiResult<Void>> restoreTrash(@PathVariable Long albumId, @PathVariable Long trashId, @AuthenticationPrincipal CustomUserDetails userDetails){
        Long userId = userDetails.getUser().getId();
        albumMemberService.checkMembership(userId, albumId);

        trashService.restoreTrash(trashId);

        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
