package com.example.dbrelations.repository;

import com.example.dbrelations.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {

    @EntityGraph(attributePaths = {"userDetailSet"},type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findUserById(Long id);

    @Query(value = "select u from User u LEFT JOIN FETCH UserDetail ud where ud.user.id=?1")
    List<User> getUserById(Long id);

    @Query("SELECT p FROM User p LEFT JOIN FETCH p.userDetailSet WHERE p.id = :id")
    Optional<User> findByIdWithCopii(@Param("id") Long id);

}
