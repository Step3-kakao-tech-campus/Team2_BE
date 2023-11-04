package com.example.team2_be.album.page;

import com.example.team2_be.album.page.dto.AlbumPageRequestDTO;
import com.example.team2_be.core.utils.ApiUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/page")
public class AlbumPageController {
    private final AlbumPageService albumPageService;

    @PostMapping("")
    public ResponseEntity<ApiUtils.ApiResult> createPage(@RequestBody AlbumPageRequestDTO requestDTO) throws JsonProcessingException {
        albumPageService.createPage(requestDTO);
        return ResponseEntity.ok(ApiUtils.success(null));
    }
}
