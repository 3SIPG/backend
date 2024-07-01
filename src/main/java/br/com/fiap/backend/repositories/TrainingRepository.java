package br.com.fiap.backend.repositories;

import br.com.fiap.backend.models.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
