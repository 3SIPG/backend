package br.com.fiap.backend.controllers;

import br.com.fiap.backend.dto.InsertTrainingData;
import br.com.fiap.backend.dto.UpdateTrainingData;
import br.com.fiap.backend.models.Training;
import br.com.fiap.backend.service.TrainingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trainings")
public class TrainingController {

    @Autowired
    private TrainingService service;

    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid InsertTrainingData data) {
        service.insert(new Training(data));
    }

    @GetMapping
    public List<Training> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Training findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateTrainingData data) {
        service.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
