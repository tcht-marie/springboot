package fr.simplon.api.repositories;

import fr.simplon.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUsername(String username);

    public Optional<User> findByUsernameAndPassword(String username, String password);

    // si les mots clés des méthodes générées par sprint boot sont pas suffisantes,
    // je peux créer ma propre méthode avec ma propre requête comme ça:

    // @Query(value = "SELECT * FROM 'users' WHERE username = $1", nativeQuery=true)
    // public Optional<User> bichette(String bichon);
}

