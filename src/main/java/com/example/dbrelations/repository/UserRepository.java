package com.example.dbrelations.repository;

import com.example.dbrelations.model.User;
import com.example.dbrelations.model.UserWithDetailsDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.nio.ReadOnlyBufferException;
import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {
    @Transactional
    @ReadOnlyProperty
    @Query("SELECT u FROM User u")
    List<User> findAllWithoutLazyAttributes();

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.details")
    List<User> findAllWithDetails();

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.addresses")
    List<User> findAllWithAddresses();

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.details LEFT JOIN FETCH u.addresses")
    List<User> findAllWithDetailsAndAddresses();

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.details")
    List<User> findDTOWithDetails();
}
