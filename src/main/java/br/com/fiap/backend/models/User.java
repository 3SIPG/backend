package br.com.fiap.backend.models;

import br.com.fiap.backend.dto.InsertUserData;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Table(name = "tb_users")
@Entity(name = "User")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    public User(InsertUserData data) {
        this.password = data.password();
        this.name = data.name();
        this.email = data.email();
    }


}
