package com.example.dbrelations.services;

import com.example.dbrelations.model.User;
import com.example.dbrelations.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Subgraph;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
@Transactional
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
        entityManager.clear();
        Optional<User> optionalUser=userRepository.findById(id);
        if(optionalUser.isPresent()){
            optionalUser.get().setUserAddresses(new ArrayList<>());
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

    public User getWithJPQL(Long id){


        Query q = this.entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.userDetailSet join fetch u.userAddresses=[]  wHERE u.id = :id");
        q.setParameter("id", id);
        return (User) q.getSingleResult();
    }


    public User getWithGraph(Long id){

        EntityGraph graph = (EntityGraph) this.entityManager.createEntityGraph(User.class);
        String[] grpnodes=graph.attributePaths();
        ArrayList<String> noduri=new ArrayList<>();
        noduri.add("userDetailSet");
        grpnodes=noduri.toArray(new String[0]);
        Map hints = new HashMap();
        hints.put("javax.persistence.loadgraph", graph);

        User user = this.entityManager.find(User.class, id, hints);
        return user;

    }

    @Transactional
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
