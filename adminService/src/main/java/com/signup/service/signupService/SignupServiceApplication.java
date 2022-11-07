package com.signup.service.signupService;

import com.signup.service.signupService.model.User;
import com.signup.service.signupService.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SignupServiceApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(SignupServiceApplication.class, args);
	}

	private final UserRepository userRepository;


	public SignupServiceApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		userRepository.save(new User("Alice","alice1234@gmail.com","1234"));
		userRepository.save(new User("Bob","bob4356@gmail.com","12345"));
		userRepository.save(new User("Carol","carol456@gmail.com","123456"));

		userRepository.findAll().forEach(System.out::println);
	}
}
