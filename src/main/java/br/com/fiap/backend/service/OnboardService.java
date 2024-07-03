package br.com.fiap.backend.service;

import br.com.fiap.backend.dto.UpdateOnboardData;
import br.com.fiap.backend.models.Onboard;
import br.com.fiap.backend.models.Video;
import br.com.fiap.backend.repositories.OnboardRepository;
import br.com.fiap.backend.repositories.VideoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OnboardService {

    @Autowired
    private OnboardRepository repository;

    @Autowired
    private VideoRepository videoRepository;

    @Transactional(readOnly = true)
    public List<Onboard> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Onboard insert(Onboard onboard) {
        return repository.save(onboard);
    }

    @Transactional(readOnly = true)
    public Onboard findById(Long id) {

        Onboard onboard = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Resource - " + id)
        );
        return onboard;
    }

    @Transactional
    public Onboard update(UpdateOnboardData entity) {
        try {
            Onboard onboard = repository.getReferenceById(entity.id());
            CopyToTraining(entity, onboard);
            onboard = repository.save(onboard);
            return onboard;
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Resource not found");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Invalid Training - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Invalid Training - id: " + id);
        }
    }

    @Transactional
    public void addVideoToTraining(Long trainingId, Video video) {
        Onboard onboard = repository.findById(trainingId).orElseThrow(
                () -> new IllegalArgumentException("Invalid Training ID - " + trainingId)
        );
        video.setOnboard(onboard);
        videoRepository.save(video);
    }

    @Transactional
    public void removeVideoFromTraining(Long trainingId, Long videoId) {
        Onboard onboard = repository.findById(trainingId).orElseThrow(
                () -> new IllegalArgumentException("Invalid Training ID - " + trainingId)
        );

        Video video = videoRepository.findById(videoId).orElseThrow(
                () -> new IllegalArgumentException("Invalid Video ID - " + videoId)
        );

        if (!video.getOnboard().getId().equals(trainingId)) {
            throw new IllegalArgumentException("Video does not belong to training - trainingId: " + trainingId + ", videoId: " + videoId);
        }

        onboard.getVideos().remove(video);
        videoRepository.deleteById(videoId);
    }

    @Transactional
    public Video findByOnboardAndVideoId(Long trainingId, Long videoId) {
        return videoRepository.findByOnboardAndVideoId(trainingId, videoId);
    }

    private void CopyToTraining(UpdateOnboardData entity, Onboard onboard) {
        onboard.setTitle(entity.title());
    }

}
