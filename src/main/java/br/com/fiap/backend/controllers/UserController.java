package br.com.fiap.backend.controllers;


import br.com.fiap.backend.dto.InsertUserData;
import br.com.fiap.backend.dto.UpdateUserData;
import br.com.fiap.backend.models.User;
import br.com.fiap.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid InsertUserData data) {
        service.insert(new User(data));
    }

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateUserData data) {
        service.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}


