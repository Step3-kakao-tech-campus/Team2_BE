package com.example.team2_be.album;

import com.example.team2_be.album.dto.AlbumCreateRequestDTO;
import com.example.team2_be.album.dto.AlbumFindAllResponseDTO;
import com.example.team2_be.album.dto.AlbumUpdaterequestDTO;
import com.example.team2_be.album.member.AlbumMemberService;
import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.user.User;
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
    private final AlbumMemberService albumMemberService;

    // 앨범 생성기능 POST "/albums"
    @PostMapping ("")
    public ResponseEntity<Void> createAlbum(@RequestBody @Valid AlbumCreateRequestDTO requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails){
        User user = userDetails.getUser();
        Album newAlbum = albumService.createAlbum(requestDTO,user);
        // album을 생성하는 유저를 albumMember로 추가
        albumMemberService.addMember(newAlbum.getId(),user.getId());
        return ResponseEntity.ok(null);
    }

    // 앨범 수정 기능 PUT "/albums/{albumId}"
    @PutMapping("/{albumId}")
    public ResponseEntity<Void> updateAlbum (@RequestBody @Valid AlbumUpdaterequestDTO requestDTO, @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long id){
        albumService.updateAlbum(requestDTO,userDetails.getUser(),id);
        return ResponseEntity.ok(null);
    }

    // 앨범 조회 기능 GET "/albums"
    @GetMapping("")
    public ResponseEntity<Void> findAllAlbum (@AuthenticationPrincipal CustomUserDetails userDetails){
        AlbumFindAllResponseDTO responseDTO = albumService.findAllAlbum(userDetails.getUser());

        return ResponseEntity.ok(null);
    }
}
