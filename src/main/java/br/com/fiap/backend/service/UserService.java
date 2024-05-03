package br.com.fiap.backend.service;

import br.com.fiap.backend.dto.UpdateUserData;
import br.com.fiap.backend.models.User;
import br.com.fiap.backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

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

        User user = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Resource - " + id)
        );
        return user;
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

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Invalid User - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Invalid User - id: " + id);
        }
    }

    private void CopyToUser(UpdateUserData entity, User user) {
        user.setNome(entity.nome());
        user.setEmail(entity.email());
        user.setSenha(entity.senha());
    }

}
