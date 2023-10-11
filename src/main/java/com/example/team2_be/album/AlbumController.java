package com.example.team2_be.album;

import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.album.dto.AlbumFindAllResponseDTO;
import com.example.team2_be.album.dto.AlbumUpdaterequestDTO;
import com.example.team2_be.core.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    // 앨범 생성기능 POST "/albums/create"
    @PostMapping ("/create")
    public ResponseEntity<?> createAlbum(@RequestBody @Valid AlbumCreateRequestDTO requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
        albumService.createAlbum(requestDTO,userDetails.getUser());

        return ResponseEntity.ok(null);
    }

    // 앨범 수정 기능 PUT "/albums/{id}/update"
    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateAlbum (@RequestBody @Valid AlbumUpdaterequestDTO requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id){
        albumService.updateAlbum(requestDTO,userDetails.getUser(),id);
        return ResponseEntity.ok(null);
    }

    // 앨범 조회 기능 GET "/albums"
    @GetMapping("")
    public ResponseEntity<?> findAllAlbum (@AuthenticationPrincipal CustomUserDetails userDetails){
        AlbumFindAllResponseDTO responseDTO = albumService.findAllAlbum(userDetails.getUser());

        return ResponseEntity.ok(null);
    }
}
