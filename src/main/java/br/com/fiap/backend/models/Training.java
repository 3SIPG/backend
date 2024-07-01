package br.com.fiap.backend.models;

import br.com.fiap.backend.dto.InsertTrainingData;
import br.com.fiap.backend.dto.InsertUserData;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "tb_trainings")
@Entity(name = "Training")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "trainings", fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();


    public Training(InsertTrainingData data) {
        this.title = data.title();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Training training = (Training) o;
        return Objects.equals(id, training.id) && Objects.equals(title, training.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

}
