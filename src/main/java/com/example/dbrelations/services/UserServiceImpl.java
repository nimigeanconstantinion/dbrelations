package com.example.dbrelations.services;

import com.example.dbrelations.model.Address;
import com.example.dbrelations.model.User;
import com.example.dbrelations.model.UserDetail;
import com.example.dbrelations.repository.UserRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentBag;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    //    private final UserRepository userRepository;
//
//
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
//
//    public UserServiceImpl(UserRepository userRepository,EntityManagerFactory entityManagerFactory){
//        this.userRepository=userRepository;
//        this.entityManagerFactory=entityManagerFactory;
//    }
//
//    @Override
//    public Optional<User> findById(Long id) {
//        EntityManager entityManager=entityManagerFactory.createEntityManager();
//        entityManager.clear();
//        Optional<User> optionalUser=userRepository.findById(id);
//        if(optionalUser.isPresent()){
//            optionalUser.get().setUserAddresses(new HashSet<>());
//            System.out.println("--- "+optionalUser.get().getUserDetailSet().size());
//
//            return optionalUser;
//        }else{
//
//            throw new RuntimeException("Am obtinut eroare");
//        }
//    }
//
//    public User getUserById(Long id){
//        EntityManager entityManager=entityManagerFactory.createEntityManager();
//
//        User u=entityManager.createQuery(
//                        "SELECT e FROM User e LEFT JOIN FETCH e.userDetailSet LEFT JOIN FETCH e.userAddresses LEFT JOIN e.userAddresses WHERE e.id = :id",
//                        User.class)
//                .setParameter("id", id)
//                .getSingleResult();
//        System.out.println(u.toString());
//        if(u.getUserDetailSet() instanceof PersistentBag<?>){
//            u.setUserDetailSet(new HashSet<>());
//        }
//        if(u.getUserAddresses() instanceof PersistentBag<?>){
//            u.setUserAddresses(new HashSet<>());
//        }
//        return u;
//    }
//
//    public User getWithJPQL(Long id){
//        EntityManager entityManager=entityManagerFactory.createEntityManager();
//
//
//        Query q = entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.userDetailSet WHERE u.id = :id");
//        q.setParameter("id", id);
//        return (User) q.getSingleResult();
//    }
//
//
//    public User getWithGraph(Long id){
//        EntityManager entityManager=entityManagerFactory.createEntityManager();
//
//        EntityGraph graph = (EntityGraph) entityManager.createEntityGraph(User.class);
//        String[] grpnodes=graph.attributePaths();
//        ArrayList<String> noduri=new ArrayList<>();
//        noduri.add("userDetailSet");
//        grpnodes=noduri.toArray(new String[0]);
//        Map hints = new HashMap();
//        hints.put("javax.persistence.loadgraph", graph);
//
//        User user = entityManager.find(User.class, id, hints);
//        return user;
//
//    }
//
//    @Transactional
//    public List<User> findAllUsers(){
//        List<User> lista=userRepository.findAll();
//
//        for(User u:lista){
//            if(!(u.getUserAddresses() instanceof PersistentCollection<?>)){
//                u.setUserAddresses(new HashSet<>());
//            }
//            if(!(u.getUserDetailSet() instanceof PersistentCollection<?>)){
//                u.setUserDetailSet(new HashSet<>());
//            }
//        }
//        return lista;
//    }
//
//    @Transactional
//    public List<User> findAllUsersWithChildren() {
//        List<User> lista = userRepository.findAllWithCopil1();
//        return lista.stream().map(u->{
//            u.setUserAddresses(new HashSet<>());
//            return u;
//        }).collect(Collectors.toList());
//    }
//    @Transactional
//    public List<User> findAllUsersWith2Children() {
//        List<User> lista = userRepository.findAllWith2Copii();
//        return lista;
////        return lista.stream().map(u->{
////            if(u.getUserDetailSet() instanceof List<UserDetail>){
////                u.setUserDetailSet(u.getUserDetailSet().stream().toList());
////            }else{
////                u.setUserDetailSet(new ArrayList<>());
////            }
////            if(u.getUserAddresses() instanceof List<Address>){
////                u.setUserAddresses(u.getUserAddresses());
////            }{
////                u.setUserAddresses(new ArrayList<>());
////            }
////            return u;
////        }).collect(Collectors.toList());
//    }
//
//    @Transactional
//    @ReadOnlyProperty
//    public List<User> findAllUsersTest() {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            Hibernate.initialize(user.getUserAddresses());
//            Hibernate.initialize(user.getUserDetailSet());
//        }
//        return users;
//    }
//
//    @Transactional
//    @ReadOnlyProperty
//    public List<User> findAllUsersWithLazyCollections() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        List<User> users = entityManager.createQuery(
//                        "SELECT DISTINCT u FROM User u " +
//                                "LEFT JOIN FETCH u.userAddresses " +
//                                "LEFT JOIN FETCH u.userDetailSet", User.class)
//                .getResultList();
//
//        entityManager.close(); // Închideți EntityManager pentru a elibera resursele
//
//        return users;
//    }

    public List<User> getWithDetails(){
        List<User> lista=userRepository.findAllWithDetails();
        return lista.stream().map(u->{
            u.setAddresses(new HashSet<>());
            return u;
        }).toList();

    }

    public List<User> getWithoutLazy(){
        List<User> lista=userRepository.findAllWithoutLazyAttributes();
        return lista.stream().map(u->{
            u.setAddresses(new HashSet<>());
            u.setDetails(new HashSet<>());
            return u;
        }).toList();

    }

    public List<User> getWithAddresses(){
        List<User> lista=userRepository.findAllWithAddresses();
        return lista.stream().map(u->{
//            u.setAddresses(new HashSet<>());
            u.setDetails(new HashSet<>());
            return u;
        }).toList();

    }

    public List<User> getAllWithLazy(){
        String hql = "FROM User u JOIN FETCH u.details JOIN FETCH u.addresses";
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(hql);
//        query.setParameter("username", "johnDoe");

        List<User> users = query.getResultList();
        return users;
    }

    public List<User> getAllWithoutAnyHQL(){
        String hql = "select NEW User(u.id) FROM User u";
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(hql);
//        query.setParameter("username", "johnDoe");

        List<User> users = query.getResultList();
        return users;
    }
    public List<User> getTest(){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(builder.construct(User.class, root.get("id"),root.get("details")));
        List<User> users = entityManager.createQuery(criteria).getResultList();
        return users;

    }

}
