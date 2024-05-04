package br.com.fiap.backend.service;

import br.com.fiap.backend.dto.UpdateUserData;
import br.com.fiap.backend.models.User;
import br.com.fiap.backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    public User insert(User user) {
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Resource - " + id));

    }

    @Transactional
    public User update(UpdateUserData entity) {
        try {
            User user = repository.getReferenceById(entity.id());
            CopyToUser(entity, user);
            user = repository.save(user);
            return user;
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Resource not found");
        }
    }


    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoResultException("User does not exist - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid User - id: " + id);
        }
    }

    private void CopyToUser(UpdateUserData entity, User user) {
        user.setLogin(entity.login());
        user.setPassword(entity.password());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
