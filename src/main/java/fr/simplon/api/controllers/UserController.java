package fr.simplon.api.controllers;

import fr.simplon.api.models.User;
import fr.simplon.api.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*le @Controller indique automatiquement que cette class est un controller*/
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Optional<User> getOneUser(@PathVariable Integer userId) {
        return userRepository.findById(userId);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        User newUser = new User(user.getUsername());
        return userRepository.save(newUser);
    }
}