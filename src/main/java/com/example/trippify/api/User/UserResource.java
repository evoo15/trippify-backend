package com.example.trippify.api.User;

import com.example.trippify.exception.ResourceNotFoundException;
import com.example.trippify.api.User.model.User;
import com.example.trippify.api.User.service.repository.UserRepository;
import com.example.trippify.security.CurrentUser;
import com.example.trippify.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/api/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
