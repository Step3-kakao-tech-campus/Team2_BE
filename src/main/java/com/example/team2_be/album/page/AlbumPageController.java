package com.example.team2_be.album.page;

import com.example.team2_be.album.page.dto.AlbumPageFindResponseDTO;
import com.example.team2_be.album.page.dto.AlbumPageUpdateRequestDTO;
import com.example.team2_be.core.utils.ApiUtils;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/albums/{albumId}/pages")
public class AlbumPageController {
    private final AlbumPageService albumPageService;

    @PutMapping("/{pageId}")
    public ResponseEntity<ApiUtils.ApiResult> updatePage(@RequestBody AlbumPageUpdateRequestDTO requestDTO, @PathVariable Long pageId) throws IOException {
        albumPageService.updatePage(requestDTO, pageId);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @PostMapping
    public ResponseEntity<ApiUtils.ApiResult> createPage(@PathVariable Long albumId) {
        albumPageService.createPage(albumId);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @GetMapping
    public ResponseEntity<ApiUtils.ApiResult> findPage(@PageableDefault(size = 4) Pageable pageable) {
        AlbumPageFindResponseDTO findDTO = albumPageService.findPage(pageable);
        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }
}
