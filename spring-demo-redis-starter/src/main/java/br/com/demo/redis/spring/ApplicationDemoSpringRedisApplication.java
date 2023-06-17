package br.com.demo.redis.spring;

import br.com.demo.redis.spring.model.UserModel;
import br.com.demo.redis.spring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApplicationDemoSpringRedisApplication implements CommandLineRunner {


	private final Logger LOG = LoggerFactory.getLogger(getClass());
	private final br.com.demo.redis.spring.repository.UserRepository userRepository;

	@Autowired
	public ApplicationDemoSpringRedisApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationDemoSpringRedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Saving UserModels. Current UserModel count is {}.", userRepository.count());
		UserModel shubham = new UserModel("Shubham", 23);
		UserModel pankaj = new UserModel("Pankaj", 22);
		UserModel lewis = new UserModel("Lewis", 15);

		userRepository.save(shubham);
		userRepository.save(pankaj);
		userRepository.save(lewis);
		LOG.info("Done saving UserModels. Data: {}.", userRepository.findAll());
	}
}
