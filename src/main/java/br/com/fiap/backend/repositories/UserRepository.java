package br.com.fiap.backend.repositories;


import br.com.fiap.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
