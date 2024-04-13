package com.example.dbrelations.services;

import com.example.dbrelations.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findById(Long id);

}
