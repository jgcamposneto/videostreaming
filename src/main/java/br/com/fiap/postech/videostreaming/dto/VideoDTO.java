package br.com.fiap.postech.videostreaming.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VideoDTO {

    private UUID id;

    private String titulo;

    private String descricao;

    private String url;

    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataPublicacao;

}
