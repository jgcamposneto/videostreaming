package br.com.fiap.postech.videostreaming.repository;

import br.com.fiap.postech.videostreaming.model.Video;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface VideoRepository extends ReactiveMongoRepository<Video, UUID> {
    Flux<Video> findAll();
}
