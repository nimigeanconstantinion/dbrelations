package com.example.dbrelations.services;

import com.example.dbrelations.model.User;
import com.example.dbrelations.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;


    public UserServiceImpl(UserRepository userRepository,EntityManager entityManager){
        this.userRepository=userRepository;
        this.entityManager=entityManager;
    }

    @Override
    public Optional<User> findById(Long id) {

        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
            System.out.println("--- "+optionalUser.get().getUserDetailSet().size());
            return optionalUser;
        }else{
            throw new RuntimeException("Am obtinut eroare");
        }
    }

    public User getUserById(Long id){
        return entityManager.createQuery(
                        "SELECT e FROM User e LEFT JOIN FETCH e.userDetailSet WHERE e.id = :id",
                        User.class)
                .setParameter("id", id)
                .getSingleResult();

    }
}
