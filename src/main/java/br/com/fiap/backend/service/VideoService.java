package br.com.fiap.backend.service;

import br.com.fiap.backend.dto.VideoData;
import br.com.fiap.backend.models.Video;
import br.com.fiap.backend.repositories.VideoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository repository;

    @Transactional(readOnly = true)
    public List<Video> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Video insert(Video video) {
        return repository.save(video);
    }

    @Transactional(readOnly = true)
    public Video findById(Long id) {

        Video video = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Resource - " + id)
        );
        return video;
    }

    @Transactional
    public Video update(VideoData entity) {
        try {
            Video video = repository.getReferenceById(entity.id());
            CopyToVideo(entity, video);
            video = repository.save(video);
            return video;
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Resource not found");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Invalid Video - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Invalid Video - id: " + id);
        }
    }

    private void CopyToVideo(VideoData entity, Video video) {
        video.setTitle(entity.title());
        video.setUrl(entity.url());
    }

}
