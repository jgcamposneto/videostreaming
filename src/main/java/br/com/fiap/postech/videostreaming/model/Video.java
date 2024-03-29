package br.com.fiap.postech.videostreaming.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
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

    private String titulo;

    private String descricao;

    private String url;

    private LocalDate dataPublicacao;

    private boolean favorito;

    @DocumentReference
    private Categoria categoria;

    public void favoritar() {
        favorito = true;
    }

    public void desfavoritar() {
        favorito = false;
    }

}
