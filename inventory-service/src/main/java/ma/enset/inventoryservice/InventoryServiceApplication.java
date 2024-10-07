package ma.enset.inventoryservice;

import ma.enset.inventoryservice.entities.Product;
import ma.enset.inventoryservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration configuration){
        configuration.exposeIdsFor(Product.class);
        return args -> {
            Stream.of("Ordinateur", "Imprimante", "souris", "USB", "TV", "Fridge", "Car").forEach(p->{
                Product product = Product.builder()
                        .name(p)
                        .price(10000+Math.random()*900)
                        .quantity((int) (Math.random()*100))
                        .build();
                productRepository.save(product);
            });
        };
    }

}
