package com.example.dbrelations.web;

import com.example.dbrelations.model.User;
import com.example.dbrelations.repository.UserRepository;
import com.example.dbrelations.services.UserService;
import com.example.dbrelations.services.UserServiceImpl;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@CrossOrigin

public class UserController {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;



    public UserController(UserServiceImpl userService,UserRepository userRepository){
        this.userRepository=userRepository;
        this.userService=userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/repoid={id}")
    public ResponseEntity<User> lazyFindById(@PathVariable Long id) {
//        return ResponseEntity.ok(userRepository.findUserById(id).get());
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/testid={id}")
    public ResponseEntity<User> findByName(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id).get());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/atestid={id}")
    public ResponseEntity<User> newFindById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/onedet={id}")
    public ResponseEntity<User> newGetById(@PathVariable Long id) {
        return ResponseEntity.ok(userRepository.findByIdWithCopii(id).get());
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/jpql={id}")
    public ResponseEntity<User> getWithJPQL(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getWithGraph(id));
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getFindall() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
