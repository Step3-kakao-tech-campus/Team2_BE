package com.example.team2_be.album.page;

import com.example.team2_be.album.member.AlbumMemberService;
import com.example.team2_be.album.page.dto.AlbumPageFindResponseDTO;
import com.example.team2_be.album.page.dto.AlbumPageUpdateRequestDTO;
import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import com.example.team2_be.trash.Trash;
import com.example.team2_be.trash.TrashService;
import com.example.team2_be.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums/{albumId}/pages")
public class AlbumPageController {
    private final AlbumPageService albumPageService;
    private final AlbumMemberService albumMemberService;
    private final TrashService trashService;

    @PutMapping("/{pageId}")
    public ResponseEntity<ApiUtils.ApiResult> updatePage(@RequestBody AlbumPageUpdateRequestDTO requestDTO, @PathVariable Long albumId, @PathVariable Long pageId, @AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {
        Long userId = userDetails.getUser().getId();
        albumMemberService.checkMembership(userId, albumId);

        albumPageService.updatePage(requestDTO, pageId);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @PostMapping
    public ResponseEntity<ApiUtils.ApiResult> createPage(@PathVariable Long albumId, @AuthenticationPrincipal CustomUserDetails userDetails) {
//        Long userId = userDetails.getUser().getId();
//        albumMemberService.checkMembership(userId, albumId);

        Long pageId = albumPageService.createPage(albumId);
        return ResponseEntity.ok(ApiUtils.success(pageId));
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult> findPage(@PageableDefault(size = 4) Pageable pageable, @PathVariable Long albumId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getUser().getId();
        albumMemberService.checkMembership(userId, albumId);

        AlbumPageFindResponseDTO findDTO = albumPageService.findPage(pageable, albumId);
        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }

    @DeleteMapping("/{pageId}")
    public ResponseEntity<ApiUtils.ApiResult> deletePage(@PathVariable Long albumId,@PathVariable Long pageId, @AuthenticationPrincipal CustomUserDetails userDetails ){
        User user = userDetails.getUser();
        albumMemberService.checkMembership(user.getId(), albumId);

        AlbumPage albumPage = albumPageService.findAlbumPageById(pageId);
        Trash trash = trashService.createTrash(user, albumPage);
        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
