package br.com.fiap.postech.videostreaming.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videos")
public class Video {

    @Id
    private UUID id;

    @NotEmpty(message = "Usuário não deve estar vazio")
    private String usuario;

    @NotEmpty(message = "Conteúdo não deve estar vazio")
    private String conteudo;

    @NotEmpty(message = "Data da publicação não deve estar vazia")
    private LocalDateTime dataPublicacao;

}
