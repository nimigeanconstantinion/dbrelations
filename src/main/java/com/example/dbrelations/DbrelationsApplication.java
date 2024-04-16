package com.example.dbrelations;

import com.example.dbrelations.model.User;
import com.example.dbrelations.model.UserDetail;
import com.example.dbrelations.repository.UserRepository;
import com.example.dbrelations.services.UserServiceImpl;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DbrelationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbrelationsApplication.class, args);
	}


//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository userRepository, UserServiceImpl userService){
//		return args -> {
////			System.out.println(userRepository.findAll().get(0).getUserDetailSet());
//			List<User> uo=userRepository.findAll();
//			for (User u:uo){
//					u.setUserDetailSet(new ArrayList<>());
//					u.setUserAddresses(new ArrayList<>());
//			}
//
//
//
//
//			System.out.println(uo.size());
//
//		};
//	}

}
