package com.example.dbrelations.web;

import com.example.dbrelations.model.User;
import com.example.dbrelations.model.UserWithDetailsDTO;
import com.example.dbrelations.repository.UserRepository;
import com.example.dbrelations.services.UserService;
import com.example.dbrelations.services.UserServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.PluralJoin;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.PluralAttribute;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@CrossOrigin

public class UserController {
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public UserController(UserServiceImpl userService,UserRepository userRepository){
        this.userRepository=userRepository;
        this.userService=userService;
    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/repoid={id}")
//    public ResponseEntity<User> lazyFindById(@PathVariable Long id) {
////        return ResponseEntity.ok(userRepository.findUserById(id).get());
//        return null;
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/testid={id}")
//    public ResponseEntity<User> findByName(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.findById(id).get());
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/atestid={id}")
//    public ResponseEntity<User> newFindById(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.getUserById(id));
//    }
//
////    @ResponseStatus(HttpStatus.OK)
////    @GetMapping("/onedet={id}")
////    public ResponseEntity<User> newGetById(@PathVariable Long id) {
////        return ResponseEntity.ok(userRepository.findByIdWithCopii(id).get());
////    }
//
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/jpql={id}")
//    public ResponseEntity<User> getWithJPQL(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.getWithGraph(id));
//    }
//
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/allusers")
//    public ResponseEntity<List<User>> getFindall() {
////        return ResponseEntity.ok(userService.findAllUsers());
//        return ResponseEntity.ok(userService.findAllUsersTest());
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usimpl")
    public ResponseEntity<List<User>> getFindallWithChildren() {
        return ResponseEntity.ok(userRepository.findAll());
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/uwdet")
    public ResponseEntity<List<User>> getWithDetails() {
        return ResponseEntity.ok(userService.getWithDetails());
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/uwgdet")
    public ResponseEntity<List<User>> getWithDetailsGraph() {
        List<User> lista=userRepository.findDTOWithDetails();
        return ResponseEntity.ok(lista.stream().map(u->{
            User us=new User();
            us.setId(u.getId());
            us.setDetails(u.getDetails());
            return us;
        }).toList());
            }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/uws")
    public ResponseEntity<List<User>> getWithoutLazy() {
        List<User> lista=userRepository.findAll();
        return ResponseEntity.ok(lista.stream().map(u->{

            u.setDetails(new HashSet<>());
            u.setAddresses(new HashSet<>());
            return u;
        }).toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/uwadd")
    public ResponseEntity<List<User>> getWithAddresses() {
        return ResponseEntity.ok(userService.getWithAddresses());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/uwall")
    public ResponseEntity<List<User>> getWithDetailsAndAddresses() {
        return ResponseEntity.ok(userRepository.findAllWithDetailsAndAddresses());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/uwallcb")
    public ResponseEntity<List<User>> getWithoutDetAndAddCB() {
//        return ResponseEntity.ok(userService.getAllWithoutLazy());
//            return ResponseEntity.ok(userService.getAllWithoutLazy());
//        EntityManager entityManager=entityManagerFactory.createEntityManager();
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> criteria = builder.createQuery(User.class);
//        Root<User> root = criteria.from(User.class);
//        criteria.select(builder.construct(User.class, root.get("id")));
//        List<User> users = entityManager.createQuery(criteria).getResultList();
///       return ResponseEntity.ok(users);
    return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/uwallcb2")
    public ResponseEntity<List<User>> getWithDetAddCB() {
            return ResponseEntity.ok(userService.getAllWithoutAnyHQL());
    }
}
