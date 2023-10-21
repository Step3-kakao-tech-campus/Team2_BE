package com.example.team2_be.trash;

import com.example.team2_be.trash.dto.TrashesFindResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class trashService {

    private final TrashJPARepository trashJPARepository;

    public TrashesFindResponseDTO findAll(Long albumId){
        List<Trash> trashes = trashJPARepository.findAllByAlbumId(albumId);

        return new TrashesFindResponseDTO(albumId, trashes);
    }
}
