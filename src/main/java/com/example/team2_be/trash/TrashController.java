package com.example.team2_be.trash;

import com.example.team2_be.core.security.CustomUserDetails;
import com.example.team2_be.core.utils.ApiUtils;
import com.example.team2_be.trash.dto.TrashesFindResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums/{albumId}/trashes")
public class TrashController {

    private final trashService trashService;

    //휴지통 조회 GET
    @GetMapping("/")
    public ResponseEntity<ApiUtils.ApiResult<TrashesFindResponseDTO>> findTrashes (@PathVariable Long albumId){
        TrashesFindResponseDTO findDTO = trashService.findAll(albumId);

        return ResponseEntity.ok(ApiUtils.success(findDTO));
    }

}
