package com.example.dbrelations;

import com.example.dbrelations.model.User;
import com.example.dbrelations.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class DbrelationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbrelationsApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository){
		return args -> {
			Optional<User> uo=userRepository.findUserById(1L);
			System.out.println(uo.get().getUserDetailSet().size());
		};
	}

}
