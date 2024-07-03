package br.com.fiap.backend.repositories;

import br.com.fiap.backend.models.Onboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OnboardRepository extends JpaRepository<Onboard, Long> {
}
