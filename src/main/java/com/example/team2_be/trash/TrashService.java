package com.example.team2_be.trash;

import com.example.team2_be.core.error.exception.NotFoundException;
import com.example.team2_be.trash.dto.TrashesFindResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrashService {

    private final TrashJPARepository trashJPARepository;

    public TrashesFindResponseDTO findTrashes(Long albumId){
        List<Trash> trashes = trashJPARepository.findAllByAlbumId(albumId);

        return new TrashesFindResponseDTO(albumId, trashes);
    }

    @Transactional
    public void restoreTrash(Long trashId){
        Trash trash = trashJPARepository.findById(trashId)
                .orElseThrow(() -> new NotFoundException("해당 페이지가 휴지통 내에 존재하지 않습니다."));

        trashJPARepository.delete(trash);
    }
}
