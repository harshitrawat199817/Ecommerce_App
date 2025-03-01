package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.JPA.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

	@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user.
     */
    public User registerUser(User user) {
		if (userRepository.getUserByEmail(user.getEmail())==null) {
			throw new RuntimeException("User with Email already exists");
		}
		if (userRepository.getUserByMobileNumber(user.getMobileNumber())==null) {
			throw new RuntimeException("User with Mobile number already exists");
		}
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     */
    public Optional<User> getUserById(Long userId) {
    	if (userRepository.findById(userId)==null) {
			throw new RuntimeException("User not found");
		}
        return userRepository.findById(userId);
    }

    /**
     * Updates user profile details.
     */
    public User updateUser(Long userId, User updatedUser) {
        return userRepository.findById(userId).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setMobileNumber(updatedUser.getMobileNumber());
            user.setAddresses(updatedUser.getAddresses());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Deletes a user.
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}

