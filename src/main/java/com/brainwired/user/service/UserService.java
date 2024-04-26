package com.brainwired.user.service;

import com.brainwired.user.model.User;
import com.brainwired.user.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id)
                .map(Optional::of)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
                    existingUser.setAddress(updatedUser.getAddress());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Custom exception class
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(Long id) {
            super("User not found with id: " + id);
        }
    }
}