package br.com.fiap.postech.videostreaming.repository;

import br.com.fiap.postech.videostreaming.model.Categoria;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface CategoriaRepository extends ReactiveMongoRepository<Categoria, UUID> {
}
