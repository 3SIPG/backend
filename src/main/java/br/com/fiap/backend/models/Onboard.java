package br.com.fiap.backend.models;

import br.com.fiap.backend.dto.InsertOnboardData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "tb_onboard")
@Entity(name = "Onboard")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Onboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "onboard", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Video> videos = new HashSet<>();

    public Onboard(InsertOnboardData data) {
        this.title = data.title();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Onboard onboard = (Onboard) o;
        return Objects.equals(id, onboard.id) && Objects.equals(title, onboard.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

}
