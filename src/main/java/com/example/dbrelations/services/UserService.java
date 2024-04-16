package com.example.dbrelations.services;

import com.example.dbrelations.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    @Transactional
    Optional<User> findById(Long id);

}
