package com.example.team2_be.album;

import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.album.dto.AlbumUpdaterequestDTO;
import com.example.team2_be.core.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequiredArgsConstructor
//@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    // 앨범 생성기능 POST
    // /albums/create
    @PostMapping ("albums/create")
    public ResponseEntity<?> createAlbums(@RequestBody @Valid AlbumCreateRequestDTO requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
        albumService.createAlbum(requestDTO,userDetails.getUser());

        return ResponseEntity.ok(null);
    }

    // 앨범 수정 기능 PUT
    // /albums/{id}/update
    @PutMapping("albums/{id}/update")
    public ResponseEntity<?> updateAlbums(@RequestBody @Valid AlbumUpdaterequestDTO requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id){
        albumService.updateAlbum(requestDTO,userDetails.getUser(),id);

        return ResponseEntity.ok(null);
    }
}
