package com.brainwired.user.controller;

import com.brainwired.user.model.User;
import com.brainwired.user.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@NoArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            Optional<User> user = userService.getUserById(id);
            return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseThrow(() -> new UserService.UserNotFoundException(id));
        } catch (UserService.UserNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Explicitly specify the type parameter
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Validated @RequestBody User updatedUser) {
        try {
            User updated = userService.updateUser(id, updatedUser);
            return new ResponseEntity<>(updated, HttpStatus.OK); // Explicitly specify the type parameter
        } catch (UserService.UserNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Explicitly specify the type parameter
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); 
    }
}