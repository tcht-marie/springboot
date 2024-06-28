package fr.simplon.api.controllers;

import fr.simplon.api.Exceptions.InvalidCredentialException;
import fr.simplon.api.models.User;
import fr.simplon.api.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.Optional;

@RestController
@RequestMapping
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public User createUser(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password
    ) throws InvalidCredentialException {
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidCredentialException("Check your credentials"));
    }

    @PostMapping("/register")
    public User createUser(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            @ModelAttribute("passwordConfirm") String passwordConfirm,
            @ModelAttribute("email") String email
    ) {
        if (password.equals(passwordConfirm)) {
            User user = new User(username);
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);
        } else {
            throw new InputMismatchException("Passwords didn't match");
        }
    }
}