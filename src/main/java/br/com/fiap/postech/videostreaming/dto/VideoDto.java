package br.com.fiap.postech.videostreaming.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoDto {

    private UUID id;

    @NotEmpty(message = "Título não deve estar vazio")
    private String titulo;

    @NotEmpty(message = "Descrição não deve estar vazia")
    private String descricao;

    @NotEmpty(message = "URL não deve estar vazia")
    private String url;

    @JsonFormat(pattern="dd-MM-yyyy")
    @NotEmpty(message = "Data da publicação não deve estar vazia")
    private LocalDate dataPublicacao;

    private boolean favorito;
}
