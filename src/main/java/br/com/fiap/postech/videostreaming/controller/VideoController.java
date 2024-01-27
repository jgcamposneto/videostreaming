package br.com.fiap.postech.videostreaming.controller;

import br.com.fiap.postech.videostreaming.dto.VideoDTO;
import br.com.fiap.postech.videostreaming.model.Video;
import br.com.fiap.postech.videostreaming.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Flux<Video> getVideos() {
        return videoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<VideoDTO> createVideo(@RequestBody VideoDTO videoDTO) {
        Video video = convertToEntity(videoDTO);
        return videoService.save(video).map(this::convertToDto);
    }

    private Video convertToEntity(VideoDTO videoDTO) {
        return modelMapper.map(videoDTO, Video.class);
    }

    private VideoDTO convertToDto(Video video) {
        return modelMapper.map(video, VideoDTO.class);
    }

}
