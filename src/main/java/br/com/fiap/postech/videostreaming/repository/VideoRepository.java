package br.com.fiap.postech.videostreaming.repository;

import br.com.fiap.postech.videostreaming.model.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface VideoRepository extends ReactiveMongoRepository<Video, UUID> {
    Flux<Video> findAllBy(Pageable pageable);

    Flux<Video> findByTituloAndDataPublicacao(String titulo, LocalDate dataPublicacao, Pageable pageable);

    Flux<Video> findByTitulo(String titulo, Pageable pageable);

    Flux<Video> findByDataPublicacao(LocalDate dataPublicacao, Pageable pageable);
}
