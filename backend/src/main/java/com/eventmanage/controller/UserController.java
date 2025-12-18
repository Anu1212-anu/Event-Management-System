package com.eventmanage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventmanage.entity.User;
import com.eventmanage.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {
	@Autowired private UserService service;
    @PostMapping public User create(@RequestBody User user) { return service.createUser(user); }
    @GetMapping public List<User> all() { return service.getAllUsers(); }

    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllUsers(@RequestParam String role) {
        if (!role.equals("ADMIN")) {
            return ResponseEntity.status(403).body("Access Denied");
        }
        return ResponseEntity.ok(service.getAllUsers());
    }

    
    @GetMapping("/{id}") public User get(@PathVariable Long id) { return service.getUserById(id); }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        User user = service.login(email, password);

        if (user == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return ResponseEntity.ok(user);
    }


}
