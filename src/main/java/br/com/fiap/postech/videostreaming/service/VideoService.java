package br.com.fiap.postech.videostreaming.service;

import br.com.fiap.postech.videostreaming.dto.VideoDto;
import br.com.fiap.postech.videostreaming.model.Categoria;
import br.com.fiap.postech.videostreaming.model.Video;
import br.com.fiap.postech.videostreaming.repository.CategoriaRepository;
import br.com.fiap.postech.videostreaming.repository.VideoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Mono<Page<Video>> findAllVideos(Pageable pageable) {
        return videoRepository.findAllBy(pageable)
                .collectList()
                .zipWith(this.videoRepository.count())
                .map(v -> new PageImpl<>(v.getT1(), pageable, v.getT2()));
    }

    public Mono<Page<Video>> findByTituloAndDataPublicacao(String titulo, LocalDate dataPublicacao, Pageable pageable) {
        return videoRepository.findByTituloAndDataPublicacao(titulo, dataPublicacao, pageable)
                .collectList()
                .zipWith(this.videoRepository.count())
                .map(v -> new PageImpl<>(v.getT1(), pageable, v.getT2()));
    }

    public Mono<Page<Video>> findByTitulo(String titulo, Pageable pageable) {
        return videoRepository.findByTitulo(titulo, pageable)
                .collectList()
                .zipWith(this.videoRepository.count())
                .map(v -> new PageImpl<>(v.getT1(), pageable, v.getT2()));
    }

    public Mono<Page<Video>> findByDataPublicacao(LocalDate dataPublicacao, Pageable pageable) {
        return videoRepository.findByDataPublicacao(dataPublicacao, pageable)
                .collectList()
                .zipWith(this.videoRepository.count())
                .map(v -> new PageImpl<>(v.getT1(), pageable, v.getT2()));
    }

    public Mono<Page<Video>> findByCategoria(UUID categoriaId, Pageable pageable) {
        Mono<Categoria> categoriaMono = categoriaRepository.findById(categoriaId);
        return categoriaMono.flatMap(categoria -> videoRepository.findByCategoria(categoria, pageable)
                .collectList()
                .zipWith(this.videoRepository.count())
                .map(v -> new PageImpl<>(v.getT1()
                        .stream()
                        .peek(video -> video.setCategoria(categoria))
                        .toList(), pageable, v.getT2())));
    }

    public Mono<Page<Video>> findByTituloAndDataPublicacaoAndCategoria(String titulo, LocalDate dataPublicacao, UUID categoriaId, Pageable pageable) {
        Mono<Categoria> categoriaMono = categoriaRepository.findById(categoriaId);
        return categoriaMono.flatMap(categoria -> videoRepository.findByTituloAndDataPublicacaoAndCategoria(titulo, dataPublicacao, categoria, pageable)
                .collectList()
                .zipWith(this.videoRepository.count())
                .map(v -> new PageImpl<>(v.getT1()
                        .stream()
                        .peek(video -> video.setCategoria(categoria))
                        .toList(), pageable, v.getT2())));
    }

    public Mono<Page<Video>> findByTituloAndCategoria(String titulo, UUID categoriaId, Pageable pageable) {
        Mono<Categoria> categoriaMono = categoriaRepository.findById(categoriaId);
        return categoriaMono.flatMap(categoria -> videoRepository.findByTituloAndCategoria(titulo, categoria, pageable)
                .collectList()
                .zipWith(this.videoRepository.count())
                .map(v -> new PageImpl<>(v.getT1()
                        .stream()
                        .peek(video -> video.setCategoria(categoria))
                        .toList(), pageable, v.getT2())));
    }

    public Mono<Page<Video>> findByDataPublicacaoAndCategoria(LocalDate dataPublicacao, UUID categoriaId, Pageable pageable) {
        Mono<Categoria> categoriaMono = categoriaRepository.findById(categoriaId);
        return categoriaMono.flatMap(categoria -> videoRepository.findByDataPublicacaoAndCategoria(dataPublicacao, categoria, pageable)
                .collectList()
                .zipWith(this.videoRepository.count())
                .map(v -> new PageImpl<>(v.getT1()
                        .stream()
                        .peek(video -> video.setCategoria(categoria))
                        .toList(), pageable, v.getT2())));
    }

    public Mono<Video> save(VideoDto videoDto) {
        if(Objects.nonNull(videoDto)) {
            Video video = this.convertToEntity(videoDto);
            video.setId(UUID.randomUUID());
            return videoRepository.save(video);
        }
        return null;
    }

    public Mono<Video> update(VideoDto videoDto) {
        return this.videoRepository.findById(videoDto.getId())
                .map(video -> this.convertToEntity(videoDto))
                .flatMap(this.videoRepository::save);
    }

    public Mono<Void> delete(UUID id) {
        return videoRepository.deleteById(id);
    }

    public Mono<Video> markAsFavorite(UUID videoId) {
        return videoRepository.findById(videoId)
                .map(video -> {
                    video.favoritar();
                    return video;
                })
                .flatMap(videoRepository::save);
    }

    public Mono<Video> removeFavorite(UUID videoId) {
        return videoRepository.findById(videoId)
                .map(video -> {
                    video.desfavoritar();
                    return video;
                })
                .flatMap(videoRepository::save);
    }

    private Video convertToEntity(VideoDto videoDTO) {
        return modelMapper.map(videoDTO, Video.class);
    }

    private VideoDto convertToDto(Video video) {
        return modelMapper.map(video, VideoDto.class);
    }

    public Mono<Categoria> saveCategoria(Categoria categoria) {
        if(Objects.nonNull(categoria)) {
            categoria.setId(UUID.randomUUID());
            return categoriaRepository.save(categoria);
        }
        return null;
    }
}
