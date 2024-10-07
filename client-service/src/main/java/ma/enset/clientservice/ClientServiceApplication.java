package ma.enset.clientservice;

import ma.enset.clientservice.entities.Customer;
import ma.enset.clientservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration configuration){
		// pour afficher l'Id:
		configuration.exposeIdsFor(Customer.class);
		return args -> {
			Stream.of("Samir", "Khalid", "Farid","Ibrahim", "Bilal", "Mohamed", "Ikram", "Souhail").forEach(c->{
				Customer customer = Customer.builder()
						.name(c)
						.email(c+"@gmail.com")
						.build();
				customerRepository.save(customer);
			});
		};
	}


}
