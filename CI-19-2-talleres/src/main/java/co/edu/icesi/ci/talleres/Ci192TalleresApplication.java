package co.edu.icesi.ci.talleres;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import co.edu.icesi.ci.talleres.dao.UserRepository;
import co.edu.icesi.ci.talleres.model.UserApp;
import co.edu.icesi.ci.talleres.model.UserType;

@SpringBootApplication
public class Ci192TalleresApplication {

	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Ci192TalleresApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {
			UserApp user = new UserApp();
			
			user.setUsername("admin");
			user.setPassword("{noop}123");
			user.setType(UserType.admin);
			userRepository.save(user);
	
			UserApp user2 = new UserApp();			
			user2.setUsername("operador");
			user2.setPassword("{noop}123");
			user2.setType(UserType.operador);
			userRepository.save(user2);
		};
	}
	

}
