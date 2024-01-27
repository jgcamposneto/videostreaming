package br.com.fiap.postech.videostreaming.service;

import br.com.fiap.postech.videostreaming.model.Video;
import br.com.fiap.postech.videostreaming.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Flux<Video> findAll() {
        return videoRepository.findAll();
    }

    public Mono<Video> save(Video video) {
        video.setId(UUID.randomUUID());
        return videoRepository.save(video);
    }

}
