package br.com.fiap.postech.videostreaming.controller;

import br.com.fiap.postech.videostreaming.dto.VideoDto;
import br.com.fiap.postech.videostreaming.model.Video;
import br.com.fiap.postech.videostreaming.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping
    public Mono<Page<Video>> getVideos(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "2") int size,
                                       @RequestParam(defaultValue = "dataPublicacao") String dataPublicacao,
                                       @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page, size, sortDirection, dataPublicacao);
        return videoService.findAllVideos(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Video> createVideo(@RequestBody VideoDto videoDTO) {
        return videoService.save(videoDTO);
    }

    @PutMapping()
    public Mono<Video> updateVideo(@RequestBody VideoDto videoDTO) {
        return videoService.update(videoDTO);
    }

    @DeleteMapping("/{videoId}")
    public void deleteVideo(@PathVariable("videoId") UUID id) {
        videoService.delete(id).subscribe();
    }
}
