package com.mehedihasandev.inventoryCRUD.controller;

import com.mehedihasandev.inventoryCRUD.model.User;
import com.mehedihasandev.inventoryCRUD.repository.UserRepository;
import com.mehedihasandev.inventoryCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @Autowired
    private UserRepository userRepository;

    // Login endpoint
    // Controller
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        // Fetch all users from the database
        List<User> users = userRepository.findAll();

        // Loop through users to check for a match
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return ResponseEntity.ok("Login successful (user)");
            }
        }

        // If no match found, return error
        return ResponseEntity.status(401).body("Invalid username or password");
    }

}
