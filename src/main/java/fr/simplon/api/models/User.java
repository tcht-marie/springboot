package fr.simplon.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
// library qui génère les getters, setters et constructeurs
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;


// Entity = permet la création d'une table (agit comme le CREATE TABLE)
@Entity

// @Data = génère le setter, le getter et le constructor pour tous les
// attributs obligatoires (non null) + toString + hashCode + equals
@Data

@RequiredArgsConstructor
// juste pour donner un nom à la table autre que celui de la class (User réservé en SQL)
@Table(name = "users")
public class User {
    // @Id = remplace le PRIMARY KEY
    @Id
    // @GeneratedValue = remplace le SERIAL
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NonNull
    private String username;

    private String name;
    private String email;
    @JsonIgnore
    private String password;

    @OneToOne
    private Cart cart;

    public User() {
    }
}