package com.example.team2_be.album;

import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.core.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequiredArgsConstructor
//@RequestMapping("/groups")
public class AlbumController {
    private final AlbumService albumService;

    // 앨범 생성기능 POST
    // /groups/create
    @PostMapping ("groups/create")
    public ResponseEntity<?> createAlbums(@RequestBody @Valid AlbumCreateRequestDTO requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
        albumService.createAlbum(requestDTO,userDetails.getUser());

        return ResponseEntity.ok(null);
    }

}
