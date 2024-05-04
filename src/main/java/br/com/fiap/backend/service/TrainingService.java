package br.com.fiap.backend.service;

import br.com.fiap.backend.dto.UpdateTrainingData;
import br.com.fiap.backend.dto.UpdateUserData;
import br.com.fiap.backend.models.Training;
import br.com.fiap.backend.models.User;
import br.com.fiap.backend.repositories.TrainingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrainingService {

    @Autowired
    private TrainingRepository repository;

    @Transactional(readOnly = true)
    public List<Training> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Training insert(Training training) {
        return repository.save(training);
    }

    @Transactional(readOnly = true)
    public Training findById(Long id) {

        Training training = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid Resource - " + id)
        );
        return training;
    }

    @Transactional
    public Training update(UpdateTrainingData entity) {
        try {
            Training training = repository.getReferenceById(entity.id());
            CopyToTraining(entity, training);
            training = repository.save(training);
            return training;
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Resource not found");
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new IllegalArgumentException("Invalid Training - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e){
            throw new IllegalArgumentException("Invalid Training - id: " + id);
        }
    }

    private void CopyToTraining(UpdateTrainingData entity, Training training) {
        training.setTitle(entity.title());
    }

}
