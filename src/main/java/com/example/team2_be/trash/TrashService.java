package com.example.team2_be.trash;

import com.example.team2_be.album.page.AlbumPage;
import com.example.team2_be.core.error.exception.NotFoundException;
import com.example.team2_be.trash.dto.TrashesFindResponseDTO;
import com.example.team2_be.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrashService {

    private final TrashJPARepository trashJPARepository;
    private final TaskScheduler taskScheduler;

    public TrashesFindResponseDTO findTrashes(Long albumId, Pageable pageable){
        Page<Trash> trashes = trashJPARepository.findAllByAlbumId(albumId, pageable);

        return new TrashesFindResponseDTO(albumId, trashes.getContent());
    }

    @Transactional
    public void restoreTrash(Long trashId){
        Trash trash = trashJPARepository.findById(trashId)
                .orElseThrow(() -> new NotFoundException("해당 페이지가 휴지통 내에 존재하지 않습니다."));

        trashJPARepository.delete(trash);
    }

    @Transactional
    @Scheduled(cron = "0 59 23 * * ?")
    public void deleteTrash(){
        List<Trash> after7days = trashJPARepository.findTrashesToDelete();
        trashJPARepository.deleteAllInBatch(after7days);
    }

    @Transactional
    public Trash createTrash(User user, AlbumPage albumPage){
        final Trash newTrash = trashJPARepository.save(Trash.builder()
                .user(user)
                .albumPage(albumPage)
                .build());

        return newTrash;
    }
}
