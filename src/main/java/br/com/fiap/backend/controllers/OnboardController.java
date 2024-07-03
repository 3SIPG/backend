package br.com.fiap.backend.controllers;

import br.com.fiap.backend.dto.InsertOnboardData;
import br.com.fiap.backend.dto.UpdateOnboardData;
import br.com.fiap.backend.dto.VideoData;
import br.com.fiap.backend.models.Onboard;
import br.com.fiap.backend.models.Video;
import br.com.fiap.backend.service.OnboardService;
import br.com.fiap.backend.service.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("onboard")
public class OnboardController {

    @Autowired
    private OnboardService service;

    @Autowired
    private VideoService videoService;

    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid InsertOnboardData data) {
        service.insert(new Onboard(data));
    }

    @GetMapping
    public List<Onboard> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Onboard findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateOnboardData data) {
        service.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/videos")
    public List<Video> findAllVideos() {
        return videoService.findAll();
    }

    @PostMapping("/{trainingId}/videos")
    @Transactional
    public void addVideoToTraining(@PathVariable Long trainingId, @RequestBody @Valid VideoData videoData) {
        Video video = new Video();
        video.setTitle(videoData.title());
        video.setUrl(videoData.url());
        service.addVideoToTraining(trainingId, video);
    }

    @DeleteMapping("/{trainingId}/videos/{videoId}")
    @Transactional
    public void deleteVideoFromTraining(@PathVariable Long trainingId, @PathVariable Long videoId) {
        service.removeVideoFromTraining(trainingId, videoId);
    }

    @GetMapping("/{trainingId}/{videoId}")
    @Transactional
    public Video findByOnboardAndVideoId(@PathVariable Long trainingId, @PathVariable Long videoId) {
        return service.findByOnboardAndVideoId(trainingId, videoId);
    }

}
