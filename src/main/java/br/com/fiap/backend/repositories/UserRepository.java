package br.com.fiap.backend.repositories;


import br.com.fiap.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin (String login);

}
