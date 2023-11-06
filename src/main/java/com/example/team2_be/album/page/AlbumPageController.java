package com.example.team2_be.album.page;


import com.example.team2_be.album.page.dto.AlbumPageUpdateRequestDTO;
import com.example.team2_be.core.utils.ApiUtils;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumPageController {
    private final AlbumPageService albumPageService;

    @PostMapping("/{albumId}/pages")
    public ResponseEntity<ApiUtils.ApiResult> createPage(@RequestBody AlbumPageUpdateRequestDTO requestDTO, @PathVariable Long albumId) throws IOException {
        albumPageService.createPage(requestDTO, albumId);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @GetMapping("/{albumId}/pages/{pageId}")
    public ResponseEntity<ApiUtils.ApiResult> find(@PathVariable Long albumId, @PathVariable Long pageId) throws IOException {
        AlbumPageFindResponseDTO findDTO = albumPageService.findPage(pageId);
        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }
}
