package fr.simplon.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor()
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NonNull
    private LocalDateTime creationDate;

    // a la création du panier, state par défaut à CART
    private CartState state = CartState.CART;

    @ManyToMany
    private List<Product> products;

    @OneToOne
    private User user;

    public Cart() {}
}

